## gRPC

This module contains articles about gRPC

### Relevant Articles:

- [Introduction to gRPC](https://www.baeldung.com/grpc-introduction)
- [Streaming with gRPC in Java](https://www.baeldung.com/java-grpc-streaming)
- [Error Handling in gRPC](https://www.baeldung.com/grpcs-error-handling)

####proto文件编译成Java源代码
>protoc --plugin=protoc-gen-grpc-java=D:\\maven-3.6.3\\repo\\io\\grpc\\protoc-gen-grpc-java\\1.42.2\\protoc-gen-grpc-java-1.42.2-windows-x86_64.exe -I=D:\\work\\source\\mysource\\tutorials-learn\\grpc\\src\\main\\proto  --java_out=D:\\work\\source\\mysource\\tutorials-learn\\grpc\\src\\main\\java  --grpc-java_out=D:\\work\\source\\mysource\\tutorials-learn\\grpc\\src\\main\\java  D:\\work\\source\\mysource\\tutorials-learn\\grpc\\src\\main\\proto\\HelloService.proto

说明：一般情况，我认为proto文件可以单独一个jar包，使用maven插件编译，更方便，目前开发过程中，使用的就是这种。