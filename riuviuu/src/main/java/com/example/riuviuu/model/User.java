package com.example.riuviuu.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data       //Annotation này được sử dụng để tự động tạo các phương thức getter, setter,
@Builder    //Lombok tự động tạo ra một lớp builder bên trong lớp hiện tại, giúp bạn tạo ra các đối
//tượng một cách dễ dàng và linh hoạt hơn. Điều này thường được sử dụng trong việc khởi
//tạo các đối tượng với nhiều trường có thể thiếu hoặc có thể được cung cấp ở một thứ tự không nhất định
@NoArgsConstructor      //Annotation này được sử dụng để tự động tạo ra một constructor không có tham số cho lớp.
//Điều này giúp bạn tạo ra các đối tượng mà không cần cung cấp bất kỳ đối số nào,
//cũng như làm cho việc sử dụng các thư viện và framework dễ dàng hơn.

@AllArgsConstructor     //Annotation này được sử dụng để tự động tạo ra một constructor
//chứa tất cả các trường dữ liệu của lớp. Điều này giúp bạn tạo ra các
//đối tượng với tất cả các trường đã được khởi tạo một cách tự động,
//tiết kiệm thời gian và công sức.
@Entity
@Table(name = "user")
public class User implements UserDetails {
    @Id
    @GeneratedValue //khai báo này jpa sẽ load tới và cho biết nó tự động tăng
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
