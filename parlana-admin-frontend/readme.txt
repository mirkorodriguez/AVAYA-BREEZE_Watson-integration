Install node:
------------
https://docs.aws.amazon.com/sdk-for-javascript/v2/developer-guide/setting-up-node-on-ec2-instance.html

Deploy:
npm install -g http-server
http-server -p 8082 &

nohup node server.js &
nohup node server.js > /dev/null 2>&1 &

nohup http-server -p 8082 > /dev/null 2>&1 &


Server on Python
sudo python -m SimpleHTTPServer 80 &

Angular:1.5.7
https://code.angularjs.org/1.5.7/angular-1.5.7.zip

Bootstrap 3.3.7
https://codeload.github.com/twbs/bootstrap/zip/v3.3.7
