# LineBot_Demo
[![Build Status](https://travis-ci.com/jack19990504/LineBot_Demo.svg?branch=master)](https://travis-ci.com/jack19990504/LineBot_Demo)
[![codecov](https://codecov.io/gh/jack19990504/LineBot_Demo/branch/master/graph/badge.svg?token=3NI6BKUMLR)](https://codecov.io/gh/jack19990504/LineBot_Demo)
## 開發環境建置

* 利用 [ngrok](https://ngrok.com/) 將本機Server (localhost) 發布到一個真實ip
* 使User傳送的訊息可以透過此ip, 再透過Line bot傳送到我們的Server


## 申請Line官方帳號

* 進入 [LineManager](https://account.line.biz/login?redirectUri=https%3A%2F%2Fdevelopers.line.biz%2Fconsole%2F&scope=line)
* 登入你個人的 line 或申請一個新的帳號
* 創建一個 Provider
* 選擇Create a line official account(choose Message API)

## 下載此份專案

* ```git clone https://github.com/jack19990504/LineBot_Demo.git```

## 設定相關金鑰

* 開啟 application.properties -> 將其修改為自己的資料庫
    * ![database](/src/main/resources/static/database.jpg)
* com.example.demo.keys -> lineKeys.class -> 修改為自己的accessToken
    * ![accessToken](/src/main/resources/static/accessToken.jpg)
    * 如果沒看到的話,點擊 message API, 至最下方點擊 issue new Channel access token

## Run this project

1. 開啟 ngrok ![ngrok1](/src/main/resources/static/ngrok1.jpg)
2. 輸入 http 8080 , 將預設的 8080 port 公開 ![ngrok2](/src/main/resources/static/ngrok2.jpg)
3. 將此串 URL 複製下來 https://XXXXXXXX.ngrok.io<br>![ngrok3](/src/main/resources/static/ngrok3.jpg)
4. 開啟 [line developers tab](https://developers.line.biz/en/)登入,並選擇先前創立的 line bot [linebot1](/src/main/resources/static/linebot1.jpg)
5. 點擊 message API ![messageAPI](/src/main/resources/static/linebot2.jpg)
6. 將第三步複製的 URL 貼到webhook中[linebot3](/src/main/resources/static/linebot3.jpg)
7. 啟動 spring boot app 
    * eclipse
        1. 右鍵專案
        2. Run as
        3. Spring boot app 
        ![spring1](/src/main/resources/static/spring1.jpg)
    * IntelliJ IDEA
        1. Run DemoApplication
        ![IntelliJ](/src/main/resources/static/IntelliJ.png)


## 文件與參考範例
* [Message API](https://developers.line.biz/en/docs/messaging-api/)
* [Some examples](https://developers.line.biz/en/docs/messaging-api/line-bot-sdk/)