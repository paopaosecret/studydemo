package com.xbing.app.component.aidl;
//第一点：AIDL文件可以分为两类:
//  一类用来声明实现了Parcelable接口的数据类型，以供其他AIDL文件使用那些非默认支持的数据类型。
//  另一类是用来定义接口方法，声明要暴露哪些接口给客户端调用

//第二点：支持的数据类型
//  基本数据类型、String、CharSequence、
//  List(里面的元素必须能够被AIDL支持，支持泛型List<String>，并且另一端实际接收的具体类始终是ArrayList)
//  Map(里面的元素必须能被AIDL支持，不支持泛型,并且另一端实际接收的具体类始终是HashMap)
//  Parcelabl：所有实现了Parcelable接口的对象
//  AIDL：所有的AIDL接口本身也可以在AIDL文件中使用

//第三点：定向Tag
//  定向Tag表示在跨进程通信中数据的流向，用于标注方法的参数值，
//      in：数据只能从客户端流向服务端
//      out:数据只能从服务端流向客户端
//      inout：数据可在服务端和客户端双向流通
//  注意：如果AIDL方法接口的参数值类型是：基本数据类型、String、CharSequence或者其他AIDL文件定义的方法接口，那么这些定向Tag只能是in，默认也是in
//       除了这些类型外，其他参数值都需要明确标注使用哪种定向Tag。

//第四点：默认情况下AIDL的调用过程是同步还是异步？
//  同步，客户端调用服务端的方法，被调用的方法运行在Binder线程池中，同时客户端线程会被挂起，如果服务端方法执行比较耗时，客户端线程长时间阻塞在这里，
//  如果客户端线程是UI线程，就会导致客户端ANR

//第五点：如何制定AIDL为异步调用？
//  通过接口回调；
//  首先需要提供一个AIDL接口，里面声明用户感兴趣的事件，然后在服务端进行注册，这样当用户感兴趣的事情发生时，服务端就会回调，
//  由于回调接口里声明的方法运行在客户端的线程池，客户端不能在它里面去访问UI相关的内容，如果要访问UI，可以使用handler切换到UI线程。
//  当页面销毁时，要记得解注册，由于是多进程，注册和解注册需要借助RemoteCallbackList来实现。

parcelable Book;