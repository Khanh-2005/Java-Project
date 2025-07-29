Đổi id webapp JSP/Servlet

1.  <groupId>com.khanh</groupId>
    <artifactId>firstwebapp</artifactId>
    <version>y</version>
    <packaging>war</packaging>
2.  <finalName>firstwebapp</finalName>

3.  Chạy mvn clean package (hoặc .bat nếu có cài)

Tạo project JSP/Servlet bằng cmd:

mvn archetype:generate -DgroupId=com.khanh.helloworld -DartifactId=HelloJSP -DarchetypeArtifactId=maven-archetype-webapp -DinteractiveMode=false

trong đó:
groupId: tên nhóm (package gốc)

artifactId: tên project

archetypeArtifactId: mẫu project dạng webapp (có web.xml)
