# job4j_forum [![Build Status](https://travis-ci.org/QmBo/job4j_forum.svg?branch=master)](https://travis-ci.org/QmBo/job4j_forum) [![codecov](https://codecov.io/gh/QmBo/job4j_forum/branch/master/graph/badge.svg)](https://codecov.io/gh/QmBo/job4j_forum) [![CodeFactor](https://www.codefactor.io/repository/github/qmbo/job4j_forum/badge)](https://www.codefactor.io/repository/github/qmbo/job4j_forum)
### Проект JavaEE демонстрирующий работу с framework'ом Spring (core/Data/Boot/Test).
Представляет собой форум.
Данный проект реализован при прохождении курса [Java Job4j.ru](https://job4j.ru/)

## Демонстрация

![](PrintScreen.png)

Проект развёрнут на портале [Heroku](https://vast-peak-67874.herokuapp.com/)

##  Примечания
Для прохождения автоматических тестов потребуется наличие:
1. [PostgreSQL](https://www.postgresql.org) 
2. Пользователь `postgres` с паролем `password`
3. База данных под названием `forum`

## Heroku
Для размещения на Heroku потребуется настроить _Config Vars_ в _Settings_: KEY = `MAVEN_SETTINGS_PATH`,
VALUE = `heroku-settings.xml`. Это необходимо для выбора правильного профиля Maven.

## Docker
Запуск проекта при помощи Docker:
1. Скопируйте проект и перейдите в его корень
2. Соберите проект при помощи Maven 
```shell script
mvn install -Pdocker -Dmaven.test.skip=true
```
3. Соберите контейнер с основной программой 
```shell script
docker build -t forum .
```
4. Запустите приложение 
```shell script
docker-compose up
```

Приложение будет доступно на 80 порту.

## Ссылки

* [Основной проект, демонстрирующий ООП, Java core, SOLID.](https://github.com/QmBo/job4j)