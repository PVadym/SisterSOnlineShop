package application.sisters.rest;

import lombok.*;

/**
 * Generalized class for REST API changes
 *
 * @param <T>
 * @author Pylypchenko
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeneralResponse<T> {

    private T data;

    public static <T> GeneralResponse<T> body(T data){
        return (GeneralResponse<T>) builder().data(data).build();

    }

}
