package service_order_api.service_order_api.security;

import lombok.Data;

@Data
public class AuthRequest {
    private String login;
    private String senha;

}
