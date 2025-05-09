
### Проект представляет собой RESTful веб-приложение для работы с датчиками и их измерениями, с использованием RestTemplate для взаимодействия между клиентом и сервером.
___
##### Технологии:
* Java 17
* Spring Boot
* Spring Data JPA
* Hibernate
* PostgreSQL
* Lombok
* Hibernate Validator
* RestTemplate

###### Функциональность:
* Серверная часть
    * Регистрация новых датчиков
    * Добавление измерений от датчиков
    * Получение списка всех датчиков
    * Получение списка всех измерений
    * Получение количества дождливых дней
* Клиентская часть - использование RestTemplate для:
    * Регистрации нового датчика
    * Отправки измерений
    * Получения информации с сервера
