package com.jobseeking.jobseekingbe.service;

import com.jobseeking.jobseekingbe.dto.request.AuthenticationRequest;
import com.jobseeking.jobseekingbe.dto.response.AuthenticationResponse;
import com.jobseeking.jobseekingbe.entity.User;
import com.jobseeking.jobseekingbe.repository.UserRepository;
import com.jobseeking.jobseekingbe.service.imp.AuthenticationServiceImp;
import com.jobseeking.jobseekingbe.service.imp.UserServiceImp;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j

public class AuthenticationService implements AuthenticationServiceImp {

    UserRepository userRepository;

    @NonFinal
    protected static final String SIGNER_KEY =
            "LHdv9EVi3BKZeBHVBzqPE35+0PzgIPPlEkDa+8WSugOj+QAXKDO3DfjPDhSYmkjN";

    @Override
    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {

        boolean authenticated = false;
        String token = null;

        if(userRepository.existsByEmail(authenticationRequest.getEmail())) {
            User user = userRepository.findByEmail(authenticationRequest.getEmail());
            authenticated = user.getPassword().equals(authenticationRequest.getPassword());
            if(authenticated) {
                token = generateToken(authenticationRequest.getEmail());
            }
        };

        return AuthenticationResponse.builder()
                .token(token)
                .authenticate(authenticated)
                .build();
    }

    private String generateToken(String email) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(email)
                .issuer("jobseeking.com")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
                ))
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header, payload);

        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }
}
