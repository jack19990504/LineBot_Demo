# LineBot_Demo

## download some necessary items

* [ngrok](https://ngrok.com/)

## apply Line Manager account

* [LineManager](https://account.line.biz/login?redirectUri=https%3A%2F%2Fdevelopers.line.biz%2Fconsole%2F&scope=line)
* login your personal Line account or create a new account
* create a line official account(choose Message API)

## clone this project

* cmd -> git clone https://github.com/jack19990504/LineBot_Demo.git

## setUp your personal configs

* application.properties -> change to your own database
* com.example.demo.keys -> lineKeys.class -> change to your own accessToken

## run this project

1. open ngrok ![ngrok1](/src/main/resources/static/ngrok1.jpg)
2. type ngrok http 8080 in cmd console ![ngrok2](/src/main/resources/static/ngrok2.jpg)
3. copy this line of URI ex : https://XXXXXXXX.ngrok.io<br>![ngrok3](/src/main/resources/static/ngrok3.jpg)
4. open your [line developers tab](https://developers.line.biz/en/), login, and choose your official account ![linebot1](/src/main/resources/static/linebot1.jpg)
5. click message API ![messageAPI](/src/main/resources/static/linebot2.jpg)
6. change the webhook uri to step 3's uri ![linebot3](/src/main/resources/static/linebot3.jpg)
7. run spring boot app 
    1. right click your project
    2. run as
    3. spring boot app 
    ![spring1](/src/main/resources/static/spring1.jpg)