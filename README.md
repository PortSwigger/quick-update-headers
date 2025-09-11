# Quick Update Headers Burp Extension

This Burp extension lets you quickly update the headers of a request inside a Repeater or Intruder tab to newer headers from the same host using the context menu. 

The main scenario for this is replacing the cookies inside an old Repeater tab with the latest cookies. The replacement headers will be selected based on the newest request with the same "Host" header value as the selected request. Headers that should be available in the context menu for replacement can be specified in the Burp settings under "Settings/Extensions/Quick Update Headers".

## Build

```sh
make
# or 
docker run --rm -u $(id -u):$(id -g) -v $(pwd):/home/gradle gradle:8.7.0-jdk17-alpine gradle build
mv ./build/libs/*.jar .
```
