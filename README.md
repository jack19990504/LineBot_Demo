# LineBot_Demo
[![Build Status](https://travis-ci.com/jack19990504/LineBot_Demo.svg?branch=master)](https://travis-ci.com/jack19990504/LineBot_Demo)
[![codecov](https://codecov.io/gh/jack19990504/LineBot_Demo/branch/master/graph/badge.svg?token=3NI6BKUMLR)](https://codecov.io/gh/jack19990504/LineBot_Demo)
## Download some necessary items

* [ngrok](https://ngrok.com/)

## Apply Line Manager account

* [LineManager](https://account.line.biz/login?redirectUri=https%3A%2F%2Fdevelopers.line.biz%2Fconsole%2F&scope=line)
* Login your personal Line account or create a new account
* Create a line official account(choose Message API)

## Clone this project

* cmd -> git clone https://github.com/jack19990504/LineBot_Demo.git

## SetUp your personal configs

* application.properties -> change to your own database
    * ![database](/src/main/resources/static/database.jpg)
* com.example.demo.keys -> lineKeys.class -> change to your own accessToken
    * ![accessToken](/src/main/resources/static/accessToken.jpg)
    * If you do not see it, please issue a new channel access token

## Run this project

1. Open ngrok ![ngrok1](/src/main/resources/static/ngrok1.jpg)
2. Type ngrok http 8080 in cmd console ![ngrok2](/src/main/resources/static/ngrok2.jpg)
3. Copy this line of URI ex : https://XXXXXXXX.ngrok.io<br>![ngrok3](/src/main/resources/static/ngrok3.jpg)
4. Open your [line developers tab](https://developers.line.biz/en/), login, and choose your official account ![linebot1](/src/main/resources/static/linebot1.jpg)
5. Click message API ![messageAPI](/src/main/resources/static/linebot2.jpg)
6. Change the webhook uri to step 3's uri ![linebot3](/src/main/resources/static/linebot3.jpg)
7. Run spring boot app 
    1. Right click your project
    2. Run as
    3. Spring boot app 
    ![spring1](/src/main/resources/static/spring1.jpg)

## Docs & References
* [Message API](https://developers.line.biz/en/docs/messaging-api/)
* [Some examples](https://developers.line.biz/en/docs/messaging-api/line-bot-sdk/)