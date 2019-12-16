# effective java 3rd edition

# 高效java第三版



## chapter2

## 第二章



**T**HIS chapter concerns creating and destroying objects: when and how to create them, when and how to avoid creating them, how to ensure they are destroyed in a timely manner, and how to manage any cleanup actions that must precede their destruction.

本章关注与创造和销毁对象：什么时候以及如何创造它们，何时及怎么避免创造它们，如何确保它们被一种及时的方式销毁，以及如何在销毁之前完成所有清理动作

### Item 1: Consider static factory methods instead of constructors

### 第一项：考虑静态工厂方法代替构造方法

The traditional way for a class to allow a client to obtain an instance is to provide a public constructor. There is another technique that should be a part of every programmer’s toolkit. A class can provide a public *static factory method*, which is simply a static method that returns an instance of the class. Here’s a simple example from Boolean (the *boxed primitive* class for boolean). This method translates a boolean primitive value into a Boolean object reference:

传统的方法是让一个类允许客户端获得实例而提供一个公共的构造器。还有一个方式：应该成为所有程序员工具包的一部分。一个类可以提供一个公共静态工厂方法，这个方法是一个简单的静态方法，返回一个实例类。这边有一个简单的Boolean类型的例子（基本类boolean的包装类）。这个方法将一个基本boolean值转变成一个Boolean对象引用：

```java
 public static Boolean valueOf(boolean b) {
    return b ? Boolean.TRUE : Boolean.FALSE;
}
```



Note that a static factory method is not the same as the *Factory Method* pattern from *Design Patterns* [Gamma95]. The static factory method described in this item has no direct equivalent in *Design Patterns*.

注意，静态工厂方法与设计模式中的工厂方法不同，静态工厂方法在此的描述并不直接等同于设计模式

A class can provide its clients with static factory methods instead of, or in addition to, public constructors. Providing a static factory method instead of a public constructor has both advantages and disadvantages.

一个类可以提供给客户端静态工厂方法去替代或者添加公共的构造器。提供一个静态的工厂方法代替公共构造器有利有弊。

**One advantage of static factory methods is that, unlike constructors, they have names.** If the parameters to a constructor do not, in and of themselves, describe the object being returned, a static factory with a well-chosen name is easier to use and the resulting client code easier to read. For example, the constructor BigInteger(int, int, Random), which returns a BigInteger that is probably prime, would have been better expressed as a static factory method named BigInteger.probablePrime. (This method was added in Java 4.)

静态工厂方法的优点是有名字而不想构造器那样。如果构造器的参数本身没有描述返回的对象，那么一个有精心选择名字的静态工厂更易于使用，并且更易于生成的客户端代码的阅读。比如说。返回一个BigInteger可能是素数的构造器 BigInteger(int,int,Random)，而静态工厂方法的表述可能更佳妥当BigInteger.probablePrime（此方法是java4中添加的）

A class can have only a single constructor with a given signature. Programmers have been known to get around this restriction by providing two constructors whose parameter lists differ only in the order of their parameter types. This is a really bad idea. The user of such an API will never be able to remember which constructor is which and will end up calling the wrong one by mistake. People reading code that uses these constructors will not know what the code does without referring to the class documentation.

一个类只能有一个给定签名的构造器。程序员解决这个限制通过提供两个构造器，只要参数列表不同于先前的参数类型顺序即可。这是一个非常糟糕的想法。比如API的使用者从不会记得哪个构造器是哪个，并且最终错误地调用错误的构造器。别人阅读这段代码使用这些构造器并不知道这是做什么用的，在没有提供这个类的文档的时候。

Because they have names, static factory methods don’t share the restriction discussed in the previous paragraph. In cases where a class seems to require multiple constructors with the same signature, replace the constructors with static factory methods and carefully chosen names to highlight their differences.

因为它们有名字，静态工厂方法不受到先前段落所讨论到的限制。在类中可能需要用到多种单一签名的构造器情况下，用静态工厂方法代替构造器且细心挑选名字去突出它们到不同。

**A second advantage of static factory methods is that, unlike constructors, they are not required to create a new object each time they’re invoked.** This allows immutable classes (Item 17) to use preconstructed instances, or to cache instances as they’re constructed, and dispense them repeatedly to avoid creating unnecessary duplicate objects. The Boolean.valueOf(boolean) method illustrates this technique: it *never* creates an object. This technique is similar to the *Flyweight* pattern [Gamma95]. It can greatly improve performance if equivalent objects are requested often, especially if they are expensive to create.

静态工厂方法的第二个优点是，不像构造器那样，它们每次被调用的时候不需要创建一个新的对象。这个不允许改变的类使用提前构造实例，或者在实例时缓存，作为构造器，且重复分配它们时没有创造不需要的重复对象。Boolean.valueOf(boolean)这个方法说明此种方式：它从不创造对象，这个方式时与Flyweight 模式相似。它可以极大地提高性能，在经常请求相等对象时，特别是当它们创建是开销很昂贵的情况下

The ability of static factory methods to return the same object from repeated invocations allows classes to maintain strict control over what instances exist at any time. Classes that do this are said to be *instance-controlled.* There are several reasons to write instance-controlled classes. Instance control allows a class to guarantee that it is a singleton (Item 3) or noninstantiable (Item 4). Also, it allows an immutable value class (Item 17) to make the guarantee that no two equal instances exist: a.equals(b) if and only if a == b. This is the basis of the *Flyweight* pattern [Gamma95]. Enum types (Item 34) provide this guarantee.

静态工厂方法被重复调用时返回同一个对象的能力，在实例存在时的任何时候允许类维持严格的控制。这样做的类被称作实例控制。写实例控制类的原因有很多。实例控制允许一个类保证它是单例或者不可实例。同时，它允许一个不可改变的值类保证没有两个相等的实例存在。： a.equals(b) 当且仅当 a==b，这就是基本的 Flyweight 模式 枚举类型 提供这个保证。

**A third advantage of static factory methods is that, unlike constructors, they can return an object of any subtype of their return type.** This gives you great flexibility in choosing the class of the returned object.

静态工厂方法的第三个优点是，不像构造器那样，它们可以返回一个返回类型的任意子类型的对象。者给你一个极大的灵活性在选取类的返回对象时。

One application of this flexibility is that an API can return objects without making their classes public. Hiding implementation classes in this fashion leads to a very compact API. This technique lends itself to *interface-based frameworks*(Item 20), where interfaces provide natural return types for static factory methods

一个灵活的应用就在于API可以在返回对象时没有将它们的类公开的。隐藏实例类用这种方式可以使API非常紧凑。这种方式借出自己适用于基于接口的框架。当接口提供自然返回类型给静态工厂方法。

Prior to Java 8, interfaces couldn’t have static methods. By convention, static factory methods for an interface named Type were put in a *noninstantiable companion class* (Item 4) named Types*.* For example, the Java Collections Framework has forty-five utility implementations of its interfaces, providing unmodifiable collections, synchronized collections, and the like. Nearly all of these implementations are exported via static factory methods in one noninstantiable class (java.util.Collections). The classes of the returned objects are all nonpublic.

java8之前，接口不能有静态方法。按照规定，一个接口里名为Type的静态工厂方法被放在一个非实例化的伙伴类（名为Type）中。比如java的集合框架有45个工具实现其借口，提供不可修改的集合，同步集合，以及几乎所有实现出口都经由静态工厂方法，在一个非实例的类中（java.util.Collections）.这些类返回的对象都是不公开的

The Collections Framework API is much smaller than it would have been had it exported forty-five separate public classes, one for each convenience implementation. It is not just the *bulk* of the API that is reduced but the *conceptual weight:*the number and difficulty of the concepts that programmers must master in order to use the API. The programmer knows that the returned object has precisely the API specified by its interface, so there is no need to read additional class documentation for the implementation class. Furthermore, using such a static factory method requires the client to refer to the returned object by interface rather than implementation class, which is generally good practice (Item 64).

集合框架API小很多了相比于它输出45个单独的公开类，每个类有便利的实现。它不只是大量的API减少，同时也减少了概念的权重：程序员必须掌握大量且困难的概念才能使用API。程序员知道这个返回对象恰好在API接口上有详细的说明。所以不需要为这个实现类读多余的文档。而且，使用静态工厂方法需要客户端通过接口而不是通过实现类接收返回值对象，这通常是一个好的实践

As of Java 8, the restriction that interfaces cannot contain static methods was eliminated, so there is typically little reason to provide a noninstantiable companion class for an interface. Many public static members that would have been at home in such a class should instead be put in the interface itself. Note, however, that it may still be necessary to put the bulk of the implementation code behind these static methods in a separate package-private class. This is because Java 8 requires all static members of an interface to be public. Java 9 allows private static methods, but static fields and static member classes are still required to be public.

从java8 开始，接口不能包含静态方法的这个限制被消除了，所以通常没有理由提供给一个非实例化的伙伴类给接口。在这样一个类中的许多公共静态成员应该放回它的接口本身。注意，但是放置大量的实现代码在静态方法下在分配私有包类时仍然是必要的。这是因为java 8 要求接口下所有静态成员都是公共的。java 9 允许私有静态方法，但是静态字段和静态成员类仍然需要时公共的。

A fourth advantage of static factories is that the class of the returned object can vary from call to call as a function of the input parameters. Any subtype of the declared return type is permissible. The class of the returned object can also vary from release to release.

静态工厂的第四个优点是返回对象的类可以根据调用不同输入参数的方法而不同。所有声明返回类型的字类型都是被允许的。返回对象的类同样可以根据发布的不同而不同。

The EnumSet class (Item 36) has no public constructors, only static factories. In the OpenJDK implementation, they return an instance of one of two subclasses, depending on the size of the underlying enum type: if it has sixty-four or fewer elements, as most enum types do, the static factories return a RegularEnumSet instance, which is backed by a single long; if the enum type has sixty-five or more elements, the factories return a JumboEnumSet instance, backed by a long array.

枚举集合类没有公有构造器，只有静态工厂，在OpenJDK实现里，它们返回一个两者之一的子类型实例，根据底层枚举类型的大小：如果小于等于64个节点，多数枚举类是这样的，静态工厂返回一个RegularEnumSet实例，返回一个long类型；如果枚举类型后多余等于65个节点，静态工厂返回JumboEnumSet实例，返回一个long类型数组

The existence of these two implementation classes is invisible to clients. If RegularEnumSet ceased to offer performance advantages for small enum types, it could be eliminated from a future release with no ill effects. Similarly, a future release could add a third or fourth implementation of EnumSet if it proved beneficial for performance. Clients neither know nor care about the class of the object they get back from the factory; they care only that it is some subclass of EnumSet.

现存的这两个实现类客户端是看不见的，如果RegularEnumSet 停止为小的枚举类型提供性能优势，它可能在未来版本中淘汰而不带来影响。同样的未来版本可以添加第三或第四个实现枚举集合，如果它提供性能上的优势。客户端同样不知道且不关心这个从工厂返回对象的类；它们只关心它是否是枚举集合的子类。

A fifth advantage of static factories is that the class of the returned object need not exist when the class containing the method is written. Such flexible static factory methods form the basis of service provider frameworks, like the Java Database Connectivity API (JDBC). A service provider framework is a system in which providers implement a service, and the system makes the implementations available to clients, decoupling the clients from the implementations.

静态工厂的第五个好处是，在编写包含该方法的类已经写好了的时候，返回对象的类不需要存在。这种灵活静态工厂方法构成服务框架基础，像Java数据库连接API（JDBC），一个服务提供框架是提供者实现一个服务的系统，并且这个系统使得实现对客户端可用，从而将客户端从实现中分离出来。

There are three essential components in a service provider framework: a service interface, which represents an implementation; a provider registration API, which providers use to register implementations; and a service access API, which clients use to obtain instances of the service. The service access API may allow clients to specify criteria for choosing an implementation. In the absence of such criteria, the API returns an instance of a default implementation, or allows the client to cycle through all available implementations. The service access API is the flexible static factory that forms the basis of the service provider framework.

在服务提供框架中有三个必不可少的组件：一个表示实现的服务接口；提供者注册API，用于提供住车实现；以及一个服务接受API，用于客户端获取服务的实例。服务接受API允许客户端选择实现的具体标准。在缺少这样的标准下，API返回一个默认实现的实例，或者允许客户端遍历所有可用实例。服务接收API是灵活静态工厂为服务框架提供基础。

An optional fourth component of a service provider framework is a service provider interface, which describes a factory object that produce instances of the service interface. In the absence of a service provider interface, implementations must be instantiated reflectively (Item 65). In the case of JDBC, Connection plays the part of the service interface, DriverManager.registerDriver is the provider registration API, DriverManager.getConnection is the service access API, and Driver is the service provider interface.

一个可选的服务提供者框架的第四个组件is服务提供者接口，这个接口描述一个提供服务接口实例工厂对象。在服务提供者接口缺失的情况下，实现必须实现反射。在JDBC的情况下，连接扮演着服务接口这部分角色，DriverManager.registerDriver 是提供者注册API，DriverManager.getConnection 是服务接收API， 而Driver是服务提供者接口。

There are many variants of the service provider framework pattern. For example, the service access API can return a richer service interface to clients than the one furnished by providers. This is the Bridge pattern [Gamma95]. Dependency injection frameworks (Item 5) can be viewed as powerful service providers. Since Java 6, the platform includes a general-purpose service provider framework, java.util.ServiceLoader, so you needn’t, and generally shouldn’t, write your own (Item 59). JDBC doesn’t use ServiceLoader, as the former predates the latter.

服务提供者框架有许多变种，比如说，服务访问API可以比一个提供者返回更丰富的服务接口到客户端这是桥接模式。依赖于注入框架，可以被视为强大的服务提供者。紫Java6之后，平台包含一个通用的服务提供者框架，java.util.ServiceLoader,所以你不需要一般也不应该自己编写。JDBC 不实用 ServiceLoader，因为前者早于后者

The main limitation of providing only static factory methods is that classes without public or protected constructors cannot be subclassed. For example, it is impossible to subclass any of the convenience implementation classes in the Collections Framework. Arguably this can be a blessing in disguise because it encourages programmers to use composition instead of inheritance (Item 18), and is required for immutable types (Item 17).

只提供静态工厂方法的主要限制是类没有公共的或者保护的构造器不能有子类。比如说，在集合框架里的任意方便实现类是不可能子类化的，可以说这是因祸得福因为它鼓励程序员使用组合而非继承，且需要不可变类型

A second shortcoming of static factory methods is that they are hard for programmers to find. They do not stand out in API documentation in the way that constructors do, so it can be difficult to figure out how to instantiate a class that provides static factory methods instead of constructors. The Javadoc tool may someday draw attention to static factory methods. In the meantime, you can reduce this problem by drawing attention to static factories in class or interface documentation and by adhering to common naming conventions. Here are some common names for static factory methods. This list is far from exhaustive:

静态工厂的第二个缺点是程序员很难找到它们。它们不会突出在API文档中，同样构造器也是。所以它很难找出如何实例化一个类提供静态工厂方法而不是构造器。Java文档工具油田可能注意到静态工厂方法。与此同时，你通过关注在类或者在接口文档中的静态工厂和通过遵守通用的命名规定可以减少这个问题。以下是一些通用的静态工厂方法名字，这个列表只是冰山一角：

• from—A type-conversion method that takes a single parameter and returns a corresponding instance of this type, for example:

from --A类型 方法类型转变输入一个单一参数返回一个相关词类型的实例，比如说

Date d = Date.from(instant); 

• of—An aggregation method that takes multiple parameters and returns an instance of this type that incorporates them, for example:

of ---一个聚合方法，接收许多参数并且返回一个这种类型实例，并把它们合并在一起，例如：

Set<Rank> faceCards = EnumSet.of(JACK, QUEEN, KING);

 • valueOf—A more verbose alternative to from and of, for example:

valueOf --- 比from和of更冗长的选择

BigInteger prime = BigInteger.valueOf(Integer.MAX_VALUE);

 • instance or getInstance—Returns an instance that is described by its parameters (if any) but cannot be said to have the same value, for example:

instance or getInstance ---返回一个描述自身参数（如果有的话）的实例，但不能说它具有相同值，例如

StackWalker luke = StackWalker.getInstance(options); 

• create or newInstance—Like instance or getInstance, except that the method guarantees that each call returns a new instance, for example:

create or newInstance ---类似于instance or getInstance，除了该方法保证每个调用返回一个新的实例

Object newArray = Array.newInstance(classObject, arrayLen); 

• getType—Like getInstance, but used if the factory method is in a different class. Type is the type of object returned by the factory method, for example:

getType --- 类似于getInstance，当工厂方法在不同个类时使用，类型是这个工厂方法对象的返回类型，例如：

FileStore fs = Files.getFileStore(path); 

• newType—Like newInstance, but used if the factory method is in a different class. Type is the type of object returned by the factory method, for example:

newType ---类似与newInstance，当工厂方法在不同类时使用，类型是这个工厂方法返回对象的类型，例如

BufferedReader br = Files.newBufferedReader(path); 

• type—A concise alternative to getType and newType, for example:

type ---getType和newType的一个简单选择例如

List<Complaint> litany = Collections.list(legacyLitany);

In summary, static factory methods and public constructors both have their uses, and it pays to understand their relative merits. Often static factories are preferable, so avoid the reflex to provide public constructors without first considering static factories.

总的来说，静态工厂方法和公有构造器都有各自的使用，这取决于明白它们各自相对的价值，通常来说静态工厂更适合选择，所以避免在没有首先考虑静态工厂的情况下使用公有构造器













