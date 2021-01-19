# tages
Задание - https://docs.google.com/document/d/1pekQRcVGT6QTXk060L_EI67nrGEwREgV2UME84DEg1Y/edit?usp=sharing

Подробности API
Пример JSON, отправляемого на POST http://localhost:8080/tages-webapp-1.0-SNAPSHOT/.rest/orders/v1/
{
"fullName" : "User",
"phoneNumber" : "8 800 555 35 35",
"email" : "user@mail.com",
"comment" : "This is comment"
}

Пример JSON, отправляемого на POST http://localhost:8080/tages-webapp-1.0-SNAPSHOT/.rest/orders/v1/{id}
{
"name" : "pict.jpg",
"content" : "base64string"
}

Сборка
Родительский проект, содержащий webapp и magnolia module.
После выполнения в директории проекта mvn clean install в
директории tages\tages-webapp\target будет .war файл,
готовый к деплою на контейнер сервлетов (например, Apache Tomcat).

Настройки role для пользователя не-админа - https://drive.google.com/file/d/1dhlpM5LcSS5dNRHejUD5GON6we_81ByU/view?usp=sharing