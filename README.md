<p align="center">
 <h2 align="center">CRUD 1 project 1 bảng mysql  spring boot vs jsp</h2>
</p>


### Bảng MySQL

Trước khi thực hiện bất kỳ thao tác nào trên dữ liệu, bạn cần tạo bảng cần thiết. Đương nhiên, tôi sẽ tạo một bảng có tênsinh_vienvien và chèn dữ liệu.

Để kiểm tra ứng dụng ngay lập tức, bạn cần có một số dữ liệu trong bảng. Bạn cũng có thể chạy một số thử nghiệm bằng cách tạo thông tin sinh viên  mới từ giao diện người dùng nếu ban đầu bảng của bạn không có bất kỳ dữ liệu nào.


```markdown
create database demoAJax
use demoAJax

create table sinh_vien(
	ma_sinh_vien int AUTO_INCREMENT,
    ten_sinh_vien nvarchar(50) not null,
     PRIMARY KEY (ma_sinh_vien)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

// thêm dữ liệu

INSERT INTO `demoajax`.`sinh_vien`
(`ma_sinh_vien`,
`ten_sinh_vien`)
VALUES (1,'le van A'),(2,'le van B')

```

### Cấu trúc dự án 

Cây thư mục dự án như sau:

![image](https://user-images.githubusercontent.com/109157942/209562467-2b46633a-713d-4d21-b990-4c7480d79dd6.png)


### Thiết lập dự án

Bạn có thể tạo một dự án dựa trên  maven trong IDE hoặc công cụ yêu thích của bạn. 

Bạn cần bao gồm các phụ thuộc bắt buộc trong  tập lệnh  **pom.xml**  để làm việc trên ứng dụng này bằng Spring Boot JPA.

```markdown
 <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-tomcat</artifactId>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <!-- https://mvnrepository.com/artifact/org.apache.tomcat.embed/tomcat-embed-jasper -->
        <dependency>
            <groupId>org.apache.tomcat.embed</groupId>
            <artifactId>tomcat-embed-jasper</artifactId>
        </dependency>
        <!-- https://mvnrepository.com/artifact/org.hibernate.validator/hibernate-validator -->
        <dependency>
            <groupId>org.hibernate.validator</groupId>
            <artifactId>hibernate-validator</artifactId>
        </dependency>
        <dependency>
            <groupId>org.glassfish.web</groupId>
            <artifactId>jakarta.servlet.jsp.jstl</artifactId>
            <version>3.0.1</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-jpa -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>
        <!-- https://mvnrepository.com/artifact/org.projectlombok/lombok-maven-plugin -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.24</version>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>org.glassfish.web</groupId>
            <artifactId>jakarta.servlet.jsp.jstl</artifactId>
            <version>2.0.0</version>
        </dependency>
        <!-- taglibs-standard-spec-*.jar -->
        <!-- http://mvnrepository.com/artifact/org.apache.taglibs/taglibs-standard-spec -->
        <dependency>
            <groupId>org.apache.taglibs</groupId>
            <artifactId>taglibs-standard-spec</artifactId>
            <version>1.2.5</version>
        </dependency>

        <!-- taglibs-standard-impl-*.jar -->
        <!-- http://mvnrepository.com/artifact/org.apache.taglibs/taglibs-standard-impl -->
        <dependency>
            <groupId>org.apache.taglibs</groupId>
            <artifactId>taglibs-standard-impl</artifactId>
            <version>1.2.5</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/jstl/jstl -->
        <dependency>
            <groupId>javax.servlet.jsp.jstl</groupId>
            <artifactId>jstl-api</artifactId>
            <version>1.2</version>
        </dependency>

}
```

config trong file **application.properties**

```markdown

spring.datasource.url=jdbc:mysql://localhost:3306/**tên dự database**
spring.datasource.username= ** username **
spring.datasource.password=  ** password**
spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
spring.datasource.sql-script-encoding=UTF-8
spring.jpa.properties.hibernate.globally_quoted_identifiers=true
spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true

spring.mvc.view.prefix: /WEB-INF/view/
spring.mvc.view.suffix: .jsp

```

### Lớp thực thể (Entity Class)

Giả sử chúng ta có lớp thực thể sau được gọi SinhVien.


```markdown
package com.example.demo.model;

import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Data
@Entity
@Table(name = "sinh_vien")
public class SinhVien {

    @Id
    @Column(name = "ma_sinh_vien")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long maSinhVien;

    @Column(name = "ten_sinh_vien", nullable = true)
    private String tenSinhVien;
}
```

>Các annotation mình sử dụng trong đoạn code trên là các annotation của JPA:

@Entity xác định lớp hiện tại là một entity.\
@Table xác định tên bảng ánh xạ sang.\
@Id xác định thuộc tính hiện tại là ID trong bảng CSDL.\
@GeneratedValue xác định kiểu sinh khóa chính, ở đây là AUTO_INCREMENT.\
@Column xác định thuộc tính hiện tại là một cột trong CSDL.\

### Spring Data JPA Repository

Spring Data JPA API cung cấp hỗ trợ kho lưu trữ cho Java Persistence API (JPA) và nó giúp giảm bớt sự phát triển của các ứng dụng cần truy cập các nguồn dữ liệu JPA.\
Tôi sẽ tạo giao diện kho lưu trữ và bạn không cần tạo bất kỳ phương thức nào trong giao diện này vì Spring cung cấp các phương thức để thực hiện các thao tác CRUD cơ bản.

```markdown
package com.example.demo.repository;

import com.example.demo.model.SinhVien;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SinhVienRepo extends JpaRepository<SinhVien, Long> {
}
```

### Service

Lớp Service nằm giữa bộ controller và lớp DAO và chứa các phương thức trừu tượng. Nói chung, bạn thực hiện gọi các hàm trong lớp dịch vụ này.

```markdown
package com.example.demo.service;

import com.example.demo.model.SinhVien;
import com.example.demo.request.SinhVienRequest;

import java.util.List;

public interface SinhVienService {

    List<SinhVien> getList();

    SinhVien insert(SinhVien sinhVien);

    boolean delete(long id);

    SinhVien update( SinhVien sinhVien);

    SinhVien findById(long id);

}
```

### implements

Lớp  **SinhVienServiceImpl** ghi đè các phương thức của  **SinhVienService**  và thực hiện logic nghiệp vụ trong lớp dịch vụ này\
Bạn nhận được kết quả của các truy vấn nối từ kho lưu trữ và chuyển cho lớp điều khiển REST.\
Tôi sử dụng cùng một phương pháp để lưu hoặc cập nhật thông tin công ty mới hoặc hiện có tương ứng.

```markdown
package com.example.demo.service.impl;

import com.example.demo.model.SinhVien;
import com.example.demo.repository.SinhVienRepo;
import com.example.demo.request.SinhVienRequest;
import com.example.demo.service.SinhVienService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SinhVienServiceImpl  implements SinhVienService {

    @Autowired
    private SinhVienRepository sinhVienRepository;

    @Override
    public List<SinhVien> getList() {
        return sinhVienRepository.findAll();
    }

    @Override
    public SinhVien insert(SinhVien sinhVien) {
        return sinhVienRepository.save(sinhVien);
    }

    @Override
    public boolean delete(long id) {
        Optional<SinhVien> sinhVien = sinhVienRepository.findById(id);
        if (sinhVien.isPresent()) {
            sinhVienRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public SinhVien update( SinhVien sinhVien) {
        SinhVien editSinhVien = sinhVienRepository.findById(sinhVien.getMaSinhVien()).orElse(null);
        sinhVien.setTenSinhVien(sinhVien.getTenSinhVien());
        return sinhVienRepository.save(sinhVien);
    }

    @Override
    public SinhVien findById(long id) {
        return sinhVienRepository.findById(id).orElse(null);
    }

}
```

### Hiển thị dữ liệu sinh viên 

1. controller

```
@Controller
public class SinhVienController {

    @Autowired
    private SinhVienService sinhVienService;

    @GetMapping("/view")
    public String home(Model model){
        List<SinhVien> sinhViens = sinhVienService.getList();
        System.out.println(sinhViens);
        model.addAttribute("data",sinhViens);
        return "index"; // return file 
    }

}

``` 


2. index.jsp

```

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Insert title here</title>
</head>
<body >
<div class="container mt-3">

    <h1>Add Sinh vien Form</h1>
    <a href="addSinhVien" class="btn btn-primary"> Add Sinh Vien </a>
    <div class="row">
        <p>${data} hello</p>
        <table class="table table-hover">
            <thead>
            <tr>
                <th scope="col">ID</th>
                <th scope="col">Name</th>
            </tr>

            </thead>

            <tbody>
            <c:forEach var="sinhVien" items="${data}">
                <tr>
                    <td class="table-plus">${sinhVien.maSinhVien} </td>
                    <td>${sinhVien.tenSinhVien}</td>
                    <td><a href="editSinhVien/${sinhVien.maSinhVien}" class="btn btn-warning">
                        Edit </a></td>
                    <td><a href="deleteSinhVien/${sinhVien.maSinhVien}"
                           class="btn btn-danger"> Delete </a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

</body>

</html>

```

### Thêm mới một sinh viên 


1. controller

```

@GetMapping("/addSinhVien")
    public String  viewAddSinhVien()
    {
        return "addSinhVien";

    }
    
    @PostMapping("/insertSinhVien")
    public String insertSinhVien(@ModelAttribute("insertSinhVien") SinhVien sinhVien){
        sinhVienService.insert(sinhVien);
        return "redirect:/view";
    }

```

2. addSinhVien.jsp

```
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <%--    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">--%>
    <title>Insert title here</title>
</head>
<body >

<div class="container mt-3">

    <h1>Add Employee Form</h1>
    <form action="insertSinhVien" method="post">

        <div class="row">
            <div class="col">
                <div class="form-group">
                    <label for="name">Name</label> <input type="text"
                                                          class="form-control" id="name" name="tenSinhVien"
                                                          placeholder="Enter Name">
                </div>
            </div>
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>

</div>

</body>

</html>

```

### Cập nhật sinh viên


1: controller

```
     @PostMapping("/editSinhVien/updateSinhVien")
    public String updateSinhVien( @ModelAttribute("sinhVien") SinhVien sinhVien){
        sinhVienService.update( sinhVien);
        return "redirect:/view";
    }
    @GetMapping("/editSinhVien/{id}")
    public String  viewUpdateSinhVien(@PathVariable("id") Long id, Model model)
    {
        model.addAttribute("sinhVien", sinhVienService.findById(id));
        return "updateSinhVien";

    }

```

2. updateSinhVien.jsp

```
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <%--    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">--%>
    <title>Insert title here</title>
</head>
<body >

<div class="container mt-3">

    <h1>Edit Sinh Vien Form</h1>
    <form action="updateSinhVien" method="POST" modelAttribute="sinhVien">

        <div class="row">
            <div class="col">
                <div class="form-group">
                    <label for="id">Id</label> <input type="text"
                                                      value="${sinhVien.maSinhVien}" class="form-control" id="id" name="maSinhVien"
                                                     >
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col">
                <div class="form-group">
                    <label for="name">Name</label>
                    <input class="form-control" id="name" name="tenSinhVien"
                           placeholder="Enter name"> ${sinhVien.tenSinhVien } </input>
                </div>
            </div>
        </div>

        <button type="submit" class="btn btn-primary">Submit</button>
    </form>

</div>
</body>

</html>

```


### Xóa Sinh viên

1: controller

```
  @GetMapping("/deleteSinhVien/{id}")
    public String deleteSinhVien(@PathVariable("id") Long id){
        sinhVienService.delete(id);
        return "redirect:/view";
    }

```

# kết luận 
Trong bài viết này, chúng ta đã học cách CRUD 1 project 1 bảng mysql  spring boot vs jsp</h2>

tất cả các mẫu mã hiển thị trong bài viết đều có sẵn [trên github](https://github.com/thangdtph27626/CRUD_Spring_JSP.github.io)
