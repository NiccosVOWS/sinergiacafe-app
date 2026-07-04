package pe.com.sinergiacafe.administracion.shared;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {

    private boolean success;
    private String message;
    private T data;

    public static <T> pe.com.sinergiacafe.administracion.shared.ApiResponse<T> ok(T data) {
        return pe.com.sinergiacafe.administracion.shared.ApiResponse.<T>builder()
                .success(true)
                .message("OK")
                .data(data)
                .build();
    }

    public static <T> pe.com.sinergiacafe.administracion.shared.ApiResponse<T> ok(String message, T data) {
        return pe.com.sinergiacafe.administracion.shared.ApiResponse.<T>builder()
                .success(true)
                .message(message)
                .data(data)
                .build();
    }

    public static <T> pe.com.sinergiacafe.administracion.shared.ApiResponse<T> error(String message) {
        return pe.com.sinergiacafe.administracion.shared.ApiResponse.<T>builder()
                .success(false)
                .message(message)
                .data(null)
                .build();
    }
}