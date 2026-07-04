package pe.com.sinergiacafe.gateway.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.sinergiacafe.gateway.security.jwt.JwtUtil;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        // Por ahora validación simple — cuando MS-RecursosHumanos esté listo
        // se conectará vía Feign para validar contra la BD
        if (request.getUsername() == null || request.getPassword() == null) {
            return ResponseEntity.badRequest()
                    .body(new LoginResponse(false, "Credenciales inválidas", null));
        }

        // Usuario de prueba hardcodeado para desarrollo
        if ("mgutierrez".equals(request.getUsername())
                && "Admin2024*".equals(request.getPassword())) {
            String token = jwtUtil.generateToken(request.getUsername(), "Administrador");
            return ResponseEntity.ok(new LoginResponse(true, "Login exitoso", token));
        }

        return ResponseEntity.status(401)
                .body(new LoginResponse(false, "Usuario o contraseña incorrectos", null));
    }

    @GetMapping("/validate")
    public ResponseEntity<LoginResponse> validate(
            @RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(401)
                    .body(new LoginResponse(false, "Token no proporcionado", null));
        }
        String token = authHeader.substring(7);
        if (jwtUtil.isTokenValid(token)) {
            return ResponseEntity.ok(
                    new LoginResponse(true, "Token válido", token));
        }
        return ResponseEntity.status(401)
                .body(new LoginResponse(false, "Token inválido o expirado", null));
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LoginRequest {
        private String username;
        private String password;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LoginResponse {
        private boolean success;
        private String message;
        private String token;
    }
}