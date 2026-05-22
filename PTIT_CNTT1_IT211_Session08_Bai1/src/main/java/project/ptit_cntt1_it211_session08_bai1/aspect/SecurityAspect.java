package project.ptit_cntt1_it211_session08_bai1.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SecurityAspect {

    @Before("""
            execution(* project.ptit_cntt1_it211_session08_bai1.service.*.deleteProduct(..))
            && args(id, username, role)
            """)
    public void checkAdmin(
            JoinPoint joinPoint,
            Long id,
            String username,
            String role
    ) {

        if (!"ADMIN".equals(role)) {
            throw new SecurityException(
                    "Chỉ ADMIN được xóa sản phẩm"
            );
        }
    }
}
