package com.rupakyeware.goatprep.controller.oauth;

import com.rupakyeware.goatprep.model.Users;
import com.rupakyeware.goatprep.service.OAuthService;
import com.rupakyeware.goatprep.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@CrossOrigin
@RestController
@RequestMapping("/oauth")
public class OAuthController {
    private final UserService userService;
    private OAuthService oAuthService;

    @Autowired
    public OAuthController(OAuthService oAuthService, UserService userService) {
        this.oAuthService = oAuthService;
        this.userService = userService;
    }

    @GetMapping("/grantcode")
    public RedirectView grantCode(@RequestParam("code") String code, @RequestParam("scope") String scope, @RequestParam("authuser") String authUser, @RequestParam("prompt") String prompt) {
        String token = oAuthService.getOauthAccessTokenGoogle(code);
        Users user = oAuthService.getProfileDetailsGoogle(token);
        String jwt = userService.handleUserOAuth(user.getUsername());
        String frontendRedirect = "https://goat-prep-frontend.pages.dev/oauth/callback?token=" + jwt;

        return new RedirectView(frontendRedirect);
    }
}
