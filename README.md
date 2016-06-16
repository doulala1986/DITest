# DITest
Dagger and ButterKnife
###DI—ButterKnife

[ButterKnife](https://jakewharton.github.io/butterknife/) (后称为**BK**) 是一个针对Android的轻量DI框架，对比RoboGuice框架，它最大的好处体现在这是**Compile Time**的框架，而并不是通过反射的方式完成注入，能够即不用付出性能的代价，又可以得到结构良好的代码。 [Github](https://github.com/JakeWharton/butterknife)  目前的版本是8.0.1，也是一个较为成熟的框架。


###Gradle 配置

in project  **grade.build**
```


buildscript {
  repositories {
    mavenCentral()
   }
  dependencies {
    classpath 'com.neenbedankt.gradle.plugins:android-apt:1.8'
  }
}
```

in app **grade.build**
```
apply plugin: 'com.neenbedankt.android-apt'//必须加到app 的build.gradle里
dependencies {
  compile 'com.jakewharton:butterknife:8.0.1'
  apt 'com.jakewharton:butterknife-compiler:8.0.1'
}

```
##Bind

如果希望使用BK的所有特性，必须使用**ButterKnife.bind**方法完成初始化, 分别针对Activity,View,Dialog提供了以下bind方式,传入**target**和**source**。

* 对Activity进行绑定

```
public static Unbinder bind(@NonNull Activity target)；
public static Unbinder bind(@NonNull Object target, @NonNull Activity source)；
```
* 针对View进行绑定，通常用在Fragment或者AdapterViewHolder中

```
 public static Unbinder bind(@NonNull View target)；
 public static Unbinder bind(@NonNull Object target, @NonNull View source)；
 
```

* 针对Dialog进行绑定


```
public static Unbinder bind(@NonNull Dialog target)；
public static Unbinder bind(@NonNull Object target, @NonNull Dialog source)
```
**target和source分别是什么 ？**

例如：
``` 
  @BindView(R.id.txv)
  TextView txv;
  
  ButterKnife.bind(fragment,view);
```

**target:** 是控件所属的对象，可能是fragment、ViewHolder，可以通过target.xxx的方式获取到属性对象。在例子中就是可以通过fragment.txv获取到对象。

**source:** 是view所在的rootview,可以通过findViewBy(R.id.xxx)完成控件绑定。

例如一个TextView txv,传入的target为Fragment frg,那么最终得到的代码会是：

```
frg.txv=(TextView)source.findViewById(R.id.txv);
```


**注意：调用bind方法的位置应该在初始界面之后，例如activity中，如果bind方法在setContentView之前，则会报错。**

```
public class BKActivity extends Activity {
    @BindView(R.id.txv)
    TextView txv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bk);
        ButterKnife.bind(this);//如果提到setContentView之前，则会发生错误。
        txv.setText("123");
    }
}
```


##Unbind
在部分场景中，需要完成release功能，比如Fragment的 **onDestoryView**生命周期中需要把view进行置空,在bind时我们获取了一个Unbinder的实例，通过调用unbind方法完成解绑。

```
public class FancyFragment extends Fragment {
  @BindView(R.id.button1) Button button1;
  @BindView(R.id.button2) Button button2;
  private Unbinder unbinder;

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fancy_fragment, container, false);
    unbinder = ButterKnife.bind(this, view);//获取unbinder
    // TODO Use fields...
    return view;
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    unbinder.unbind();//完成解绑
  }
}

```




##Bind View

通常我们使用 **findViewById** 方法初始化布局文件中的控件与后台实例的映射关系，我们需要使用并不简短的代码完成这个工作，这些工作确实会一定程度上影响开发效率，对代码阅读帮助不大，并可能造成NullPointException或者ClassParseException。

在代码中使用**@BindView**注释控件可以完成,在bind的代码中已经有所体现




##Bind Resource

```
 @BindString(R.string.title) String title;
 @BindDrawable(R.drawable.graphic) Drawable graphic;
 @BindColor(R.color.red) int red; // int or ColorStateList field
 @BindDimen(R.dimen.spacer) Float spacer; /
```


##Apply Properties

通过ButterKnife.apply方法可以完成属性的绑定处理，个人认为用处不大,略。


##Bind Listener

使用 **@OnClick** 针对特定id的控件进行onClick事件注入，同样的功能的标签还包括： **@OnItemSelected @OnCheckedChanged @OnEditorAction @OnItemClick @OnFocusChange @OnItemLongClick @OnLongClick 
@OnPageChange @OnTextChanged @OnTouch** 等熟悉的标签。


```
@OnClick(R.id.submit)
public void submit() {

}

//如果参数中指定了控件的类型，会进行自动的转换操作
@OnClick(R.id.submit)
public void sayHi(Button button) {
  button.setText("Hello!");
}
```


不知道注入的方法的完整参数怎么办？我们可以点击进入注入标签的声明，以@OnItemClick举例，parameters的列表说明了所有参数，结合OnItemClickListener 就能够比较清楚的了解了：

```
声明片段：

@ListenerClass(
    targetType = "android.widget.AdapterView<?>",
    setter = "setOnItemClickListener",
    type = "android.widget.AdapterView.OnItemClickListener",
    method = @ListenerMethod(
        name = "onItemClick",
        parameters = {
            "android.widget.AdapterView<?>",
            "android.view.View",
            "int",
            "long"
        }
    )
)

```



可以在自定义控件中直接注入点击事件，但是一定要在合适的位置调用ButterKnife.bind方法

```
public class BindListenerButton extends Button {

    public BindListenerButton(Context context) {
        super(context);
        ButterKnife.bind(this);//如果没有这一句，无法完成事件注入
    }

    @OnClick
    public void click(){
        Log.e("click","click");
    }
}

参数的问题：
1.可以没有参数；
2.参数可以减少，比如4个参数，可以写3个,2个,1个都可以；
3.参数的类型（View 的可以转换成具体类型，如是Button的点击就可以写Button）；
4.有多个参数的，参数位置可以改变，如 onItemClick(View view,int position) 与 onItemClick(int position ,View view)等效；
```

##Others
可以使用 **ButterKnife.findById** 方法完成注入，这个方法借助泛型完成了类型的转换，但是又多了一个参数，各有利弊吧，有需要的时候可以用用。


```
View view = LayoutInflater.from(context).inflate(R.layout.thing, null);
TextView firstName = ButterKnife.findById(view, R.id.first_name);
TextView lastName = ButterKnife.findById(view, R.id.last_name);
ImageView photo = ButterKnife.findById(view, R.id.photo);
```

##Plugin

[android-butterknife-zelezny](https://github.com/avast/android-butterknife-zelezny) 通过这个插件可以快速的完成onClick和bindView操作




#DI—Dagger

Dagger2 是google公司开发的**Compile Time**的支持Java全栈的DI框架。**对于不稳定的开发团队，不适合用dagger，学习成本太高。Dagger实在太晦涩了。~~**

[github](https://github.com/google/dagger) 

[gradle](http://mvnrepository.com/artifact/com.google.dagger/dagger/2.4#gradle)


[DOC](http://google.github.io/dagger/)


###1、Gradle 配置


**project build.gradle**
```
buildscript {
    repositories {
        jcenter()
    }
    dependencies {
       classpath 'com.neenbedankt.gradle.plugins:android-apt:1.4'
    }
}
```


**app build.gradle**
```
apply plugin: 'com.neenbedankt.android-apt' // 注释处理

dependencies {
    apt 'com.google.dagger:dagger-compiler:2.4'
    compile 'com.google.dagger:dagger:2.4'
}
```




###2、依赖注入的方式

依赖注入必须通过Component完成注入,设置注入方式大致是三种，按照顺序依次检索：

**1、Module类**  
  使用**@Module**进行注解,类似工厂类的作用，主要用来完成对将要注入的类型进行具体的实例化，通过指定不同的Module或者修改实例化方法完成，使用**@Provides**标签对实例化的方法进行注解，命名按照**provideXXX**的格式进行命名。Component会优先使用对应Module进行注入。如果module的方法中存在参数，那么参数也需要在某处使用@Provides进行注解。


```
by convention, @Provides methods are named with a provide prefix and module classes are named with a Module suffix.

```


```
@Module
public class AppModule {
    Context context;
    public AppModule(Context appContext){
        this.context=appContext;
    }
    @Provides
    @Singleton
    public CacheManager provideCacheManager(){
        return new CacheManager(context);
    }
    @Provides
    @Singleton
    public Context provideContext(){
        return context;
    }
}
```

**2、Inject标签**

使用**@Inject**完成注入标注，可以对**构造函数**进行注解，也可以对依赖的对象进行注解，定义Inject的方式，检索优先级低于Module。必须借助component才能生效。

```
public class FileManager {
    Context context;
    @Inject
    public FileManager(Context context){
        this.context=context;
        Log.e("inject","FileManager");
    }

    public Context getContext(){
        return context;
    }
}
```
* **情况1：**在Component中，会根据依赖的类去匹配Inject的构造函数，当发现可以匹配的构造函数，完成注入。

```
@Singleton
@Component
public interface ApplicationComponent {
    FileManager getFileManager();
}
```

* **情况2：**在其他类中，当Component通过inject方法完成了注入，则可以直接使用@Inject直接注入


```
@Singleton
@Component
public interface ApplicationComponent {
    FileManager getFileManager();
    void inject(CTSIApplication application);//声明注入方法
}

public class CTSIApplication extends Application {
    ApplicationComponent applicationComponent;
    @Inject FileManager fileManager;
    
    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent= DaggerApplicationComponent.builder().appModule(new AppModule(CTSIApplication.this)).build();
        applicationComponent.inject(this);//完成注入
        if(fileManager!=null){
            Log.e("inject","success");
        }
    }
}

```
**3、Component**


Component是整个DI的核心类，所有的载体类（如Application Activtiy等）必须借助Component完成注入，Component里面可以
```
@Singleton
@Component(modules = {AppModule.class})
public interface ApplicationComponent {

    Context getContext();

    CacheManager getCacheManager();

    FileManager getFileManager();
   

    void inject(CTSIApplication application);//在编译时变成void injectMembers(T instance); 只有在module无法注入所有对象时才会用到


   
   /**
   * Injects dependencies into the fields and methods of {@code instance}. Ignores the presence or
   * absence of an injectable constructor.
   *
   * <p>Whenever the object graph creates an instance, it performs this injection automatically
   * (after first performing constructor injection), so if you're able to let the object graph
   * create all your objects for you, you'll never need to use this method.
   *
   * @param instance into which members are to be injected
   * @throws NullPointerException if {@code instance} is {@code null}
   */
    //void injectMembers(T instance);

}

```
  
4、官方建议？？？

* Those declared by @Provides methods within a @Module referenced directly by @Component.modules or transitively via @Module.includes
* Any type with an @Inject constructor that is unscoped or has a @Scope annotation that matches one of the component’s scopes
* The component provision methods of the component dependencies
* The component itself
* Unqualified builders for any included subcomponent
* Provider or Lazy wrappers for any of the above bindings
* A Provider of a Lazy of any of the above bindings (e.g., Provider<Lazy<CoffeeMaker>>)
* A MembersInjector for any type


###@Qualifier

@Qualifier用来处理同一个module里同类型的注入的问题，如下，我们有两个Device需要注入，如何选择注入方法则需要依赖@XiaomiQualifier 或者@HuaweiQualifier区分：

```
@Module
public class ScopeModule {

    @Provides
    @XiaomiQualifier
    Device providerXiaomi() {
        return new Device("xiaomi");
    }


    @Provides
    @HuaweiQualifier
    Device providerHuawei() {
        return new Device("huawei");
    }
}
```
声明：
```
@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface HuaweiQualifier {
}


```
被不同Qualifier修饰的对象会有不同的注入
```
public class Activity_Scope extends BaseActivity {

    @Inject
    @HuaweiQualifier
    Device device;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerScopeComponent.builder().scopeModule(new ScopeModule()).build().inject(this);
        //这里需要注意的是scopeModule如果使用默认构造函数，可以不指定Module，在生成的代码中会自动添加Module
        Log.e("device", device.getName());

    }
}
```
生成build（）代码如下：


```
public ScopeComponent build() {
      if (scopeModule == null) {
        this.scopeModule = new ScopeModule();
      }
      if (applicationComponent == null) {
        throw new IllegalStateException(
            ApplicationComponent.class.getCanonicalName() + " must be set");
      }
      return new DaggerScopeComponent(this);
    }


```



### Lazy < T >

使用lazy<T>可以进行延迟加载，使用lazyObject.get()获取实例

### Provider < T >

使用Provider<T>可以进行泛型加载，使用providerObject.get()获取实例


###SubCompoent

[参考连接](http://blog.csdn.net/qq_17766199/article/details/50606011)
SubCompoent相对是比较难理解的，可以理解是跟Component有继承关系，也就是说可以直接使用Component的注入逻辑，但是要求由Component创建。如下所示：

```
@ActivtiyScope
@Component(dependencies = ApplicationComponent.class, modules = {ScopeModule.class})
public interface ScopeComponent {
    SubComponent plus();   //这里如果不传递Module，会自动使用默认构造函数创建SubCompoent的Module
    
   //SubComponent plus(SubModule subModule); 
}
```

在Compoent生成代码如下：

```
private final class SubComponentImpl implements SubComponent {
    private final SubModule subModule;

    private Provider<Customer> provideCustomerProvider;

    private MembersInjector<Activity_Scope> activity_ScopeMembersInjector;

    private SubComponentImpl() {
      this.subModule = new SubModule();
      initialize();
    }
    
   
   //   private SubComponentImpl(SubModule subModule) {
   //    this.subModule = Preconditions.checkNotNull(subModule);
   //    initialize();
   // }
    

    @SuppressWarnings("unchecked")
    private void initialize() {

      this.provideCustomerProvider = SubModule_ProvideCustomerFactory.create(subModule);

      this.activity_ScopeMembersInjector =
          Activity_Scope_MembersInjector.create(
              DaggerScopeComponent.this.providerHuaweiProvider,
              DaggerScopeComponent.this.providerXiaomiProvider,
              provideCustomerProvider,
              DaggerScopeComponent.this.getCacheManagerProvider);
    }

    @Override
    public void inject(Activity_Scope activity) {
      activity_ScopeMembersInjector.injectMembers(activity);
    }
  }
```

当我们在使用时我们需要通过Component的方法获取到SubComponent，然后使用SubComponent的Inject等方法完成对Activity的注入，非常Nice的思路。

```
  ScopeComponent component = DaggerScopeComponent.builder().applicationComponent(CTSIApplication.get(this).getApplicationComponent()).build();
  component.plus().inject(this);
```

**如何使用：**

有一个思路，我会在Activity的基类里面写一个CommonCompoent，各具体的Activity使用SubCompoent进行注入。这个CommonCompoent主要的作用有两个:
    
  1、连接AppComponent，获得AppCompoent里面注入的单例对象，如：CacheManager、FileManager、SkinManager等等。
  
  2、维护SubComponent，作为SubCompoent的工厂类，提供各SubComponent的实例。
  
  
  

  
  
  
  
  
  
  
  
  
  
**CommonComponent:** 
  ```
@ActivtiyScope
@Component(dependencies = ApplicationComponent.class, modules = {ScopeModule.class})
public interface CommonComponent {

    SubComponent plus(SubModule subModule);

}

  
  ```
**  BaseActivity:**
  ```
  public class BaseActivity extends FragmentActivity {

    CommonComponent commonComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        commonComponent = DaggerCommonComponent.builder().applicationComponent(CTSIApplication.get(this).getApplicationComponent()).build();
    }
    public CommonComponent getCommonComponent() {
        return commonComponent;
    }
}

  ```
** SubActivity:**
 ``` 
 @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getCommonComponent().plus().inject(this);
    }

 ```
  
  ** 为什么不用dependencies Component处理依赖关系？**
  
 dependencies通常用来处理不同scope之间的关系，不是用来处理继承关系，如果使用的@Scope标签，dependencies要求被依赖的Component和依赖的Component之间的Scope必须在Scope上要有上下关系。所以我们如果希望在@ActivityScope、@FragmentScope进行扩展，就不能使用dependencies。
  
  ![](F4F7ACC6-9BE9-4883-BF11-B3C9ACB8804F.png)
  
 

###Scope
[参考连接1](http://www.cnblogs.com/tiantianbyconan/archive/2016/01/02/5095426.html)
[参考连接2](http://blog.piasy.com//2016/04/11/Dagger2-Scope-Instance/)

Scope就是确保在每次注入过程中（调用build时），确保Module中被scope修饰的class只会进行一次注入(整个Scope只维护一个实例)，他能够帮我们处理不同层级的对象关系。

如果你需要确保对象在某个时间范围内唯一，请使用scope，如果需要跨Scope进行对象注入，比如在@ActivityScope的范围内使用@ApplicaionScope里的单例，请使用Denpendancies；


```
component 的 inject 函数不要声明基类参数；
Scope 注解必须用在 module 的 provide 方法上，否则并不能达到局部单例的效果；
如果 module 的 provide 方法使用了 scope 注解，那么 component 就必须使用同一个注解，否则编译会失败；
如果 module 的 provide 方法没有使用 scope 注解，那么 component 和 module 是否加注解都无关紧要，可以通过编译，但是没有局部单例效果；
对于直接使用 @Inject 构造函数的依赖，如果把 scope 注解放到它的类上，而不是构造函数上，就能达到局部单例的效果了；

```


###SubModule
SubModule是参考SubComponent的概念，并没有SubModule类，Module可以通过inculde方便的扩展、复用其他Module。这种设计能够帮助我们方便的设计我们的Module结构。










