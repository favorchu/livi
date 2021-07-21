package com.livi.demo.controller.authen;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.livi.demo.common.model.jpa.MockRepository;
import com.livi.demo.common.model.pojo.TaUser;
import com.livi.demo.common.service.CryptoService;
import com.livi.demo.common.service.JwtServivce;
import com.livi.demo.common.utils.PasswordUtils;
import com.livi.demo.controller.AbsRestController;
import com.livi.demo.exception.BusinessRuntimeExcepion;
import com.livi.demo.security.RequestAudit;

@RestController
@RequestMapping("/auth/v1")
public class AuthenController extends AbsRestController {

	private static final int TOKEN_LIFE = 60;

	@Autowired
	private MockRepository mockRepository;

	@Autowired
	private JwtServivce jwtServivce;

	@Autowired
	private CryptoService cryptoService;

	@RequestAudit
	@RequestMapping(value = { "/login" }, method = { RequestMethod.POST })
	public LoginRespVO login(@RequestBody LoginReqVO logReq) {

		TaUser user = mockRepository.getUserByIDWithCache(StringUtils.lowerCase(logReq.getUserId()));

		if (user == null)
			throw new BusinessRuntimeExcepion("User not found");
		if (!PasswordUtils.validatePassword(user.getPassword(), logReq.getPassword()))
			throw new BusinessRuntimeExcepion("Invalid password");

		String refreshToken = cryptoService.encrypt(logReq.getUserId().toLowerCase());
		String accessToken = jwtServivce.createSecretJwt(logReq.getUserId().toLowerCase(), TOKEN_LIFE);
		user.setOauthToken(refreshToken);

		LoginRespVO loginRespVO = new LoginRespVO();
		loginRespVO.setAccessToken(accessToken);
		loginRespVO.setRefreshToken(refreshToken);
		loginRespVO.setExpires(TOKEN_LIFE * 60);
		return loginRespVO;
	}

	@RequestAudit
	@RequestMapping(value = { "/refresh" }, method = { RequestMethod.POST })
	public LoginRespVO login(@RequestParam("token") String token) {
		String userId = cryptoService.decrypt(token);
		TaUser user = mockRepository.getUserByIDWithCache(StringUtils.lowerCase(userId));

		if (user == null)
			throw new BusinessRuntimeExcepion("User not found");
		if (!StringUtils.equals(token, user.getOauthToken()))
			throw new BusinessRuntimeExcepion("Invalid token");

		String accessToken = jwtServivce.createSecretJwt(userId.toLowerCase(), TOKEN_LIFE);

		LoginRespVO loginRespVO = new LoginRespVO();
		loginRespVO.setAccessToken(accessToken);
		loginRespVO.setExpires(TOKEN_LIFE * 60);
		return loginRespVO;
	}

}
