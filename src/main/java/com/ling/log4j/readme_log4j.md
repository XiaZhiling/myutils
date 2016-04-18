一、Log4J的使用事项：

1.加入jar包支持

	<dependency>
		<groupId>log4j</groupId>
		<artifactId>log4j</artifactId>
		<version>1.2.17</version>
	</dependency>
	
2.src/main/resources路径创建并设置log4j.properties
	
	参考src/main/resources/log4j.properties
	定义配置文件：
	1>配置根Logger
		log4j.rootLogger = [ level ] , appenderName, appenderName, …
		level[OFF|FATAL|ERROR|WARN|INFO|DEBUG|ALL|自定义级别]优先级由高到低，建议只使用四个级别[ERROR|WARN|INFO|DEBUG]
		输出level日志的信息  大于设置level的信息:(级别是定义在org.apache.log4j.Level类)
			off 最高等级，用于关闭所有日志记录。
			fatal 指出每个严重的错误事件将会导致应用程序的退出。
			error 指出虽然发生错误事件，但仍然不影响系统的继续运行。
			warm 表明会出现潜在的错误情形。
			info 一般和在粗粒度级别上，强调应用程序的运行全程。
			debug 一般用于细粒度级别上，对调试应用程序非常有帮助。
			all 最低等级，用于打开所有日志记录。
		appenderName就是指日志输出的位置
	2>配置日志信息输出目的地Appender
		log4j.appender.appenderName = fully.qualified.name.of.appender.class  
		log4j.appender.appenderName.option1 = value1  
		… 
		log4j.appender.appenderName.option = valueN
		
		Appender的类型：
			org.apache.log4j.ConsoleAppender（控制台）:
				>Threshold=WARN:指定日志消息的输出最低层次。
				>ImmediateFlush=true:默认值是true,意谓着所有的消息都会被立即输出。
				>Target=System.err：默认情况下是：System.out,指定输出控制台
			org.apache.log4j.FileAppender（文件）:
				>Threshold=WARN:指定日志消息的输出最低层次。
				>ImmediateFlush=true:默认值是true,意谓着所有的消息都会被立即输出。
				>File=mylog.txt:指定消息输出到mylog.txt文件。
				>Append=false:默认值是true,即将消息增加到指定文件中，false指将消息覆盖指定的文件内容。  
			org.apache.log4j.DailyRollingFileAppender（每天产生一个日志文件）:
				>Threshold=WARN:指定日志消息的输出最低层次。
				>ImmediateFlush=true:默认值是true,意谓着所有的消息都会被立即输出。
				>File=mylog.txt:指定消息输出到mylog.txt文件。
				>Append=false:默认值是true,即将消息增加到指定文件中，false指将消息覆盖指定的文件内容。
				>DatePattern=’.'yyyy-ww:每周滚动一次文件，即每周产生一个新的文件。当然也可以指定按月、周、天、时和分。即对应的格式如下：
				  1)’.'yyyy-MM: 每月
				  2)’.'yyyy-ww: 每周
				  3)’.'yyyy-MM-dd: 每天
				  4)’.'yyyy-MM-dd-a: 每天两次
				  5)’.'yyyy-MM-dd-HH: 每小时
				  6)’.'yyyy-MM-dd-HH-mm: 每分钟  
			org.apache.log4j.RollingFileAppender（文件大小到达指定尺寸的时候产生一个新的文件）:
				>Threshold=WARN:指定日志消息的输出最低层次。
				>ImmediateFlush=true:默认值是true,意谓着所有的消息都会被立即输出。
				>File=mylog.txt:指定消息输出到mylog.txt文件。
				>Append=false:默认值是true,即将消息增加到指定文件中，false指将消息覆盖指定的文件内容。
				>MaxFileSize=100KB: 后缀可以是KB, MB 或者是 GB. 在日志文件到达该大小时，将会自动滚动，即将原来的内容移到mylog.log.1文件。
				>MaxBackupIndex=2:指定可以产生的滚动文件的最大数。
			org.apache.log4j.WriterAppender（将日志信息以流格式发送到任意指定的地方）
	3>配置日志信息的格式（布局）
		log4j.appender.appenderName.layout = fully.qualified.name.of.layout.class  
		log4j.appender.appenderName.layout.option1 = value1  
		…  
		log4j.appender.appenderName.layout.option = valueN
		
		Log4j提供的layout种类：
			org.apache.log4j.HTMLLayout（以HTML表格形式布局），  
			org.apache.log4j.PatternLayout（可以灵活地指定布局模式），  
			org.apache.log4j.SimpleLayout（包含日志信息的级别和信息字符串），  
			org.apache.log4j.TTCCLayout（包含日志产生的时间、线程、类别等等信息）
	4>Log4J采用类似C语言中的printf函数的打印格式格式化日志信息，打印参数如下：
	 	%m 输出代码中指定的消息
	 	%p: 输出日志信息优先级，即DEBUG，INFO，WARN，ERROR，FATAL,
		%d: 输出日志时间点的日期或时间，默认格式为ISO8601，也可以在其后指定格式，
			比如：%d{yyy MMM dd HH:mm:ss,SSS}，输出类似：2002年10月18日 22：10：28，921
		%r: 输出自应用启动到输出该log信息耗费的毫秒数
		%c: 输出日志信息所属的类目，通常就是所在类的全名
		%t: 输出产生该日志事件的线程名
		%l: 输出日志事件的发生位置，相当于%C.%M(%F:%L)的组合,包括类目名、发生的线程，以及在代码中的行数。举例：Testlog4.main(TestLog4.java:10)
		%x: 输出和当前线程相关联的NDC(嵌套诊断环境),尤其用到像java servlets这样的多客户多线程的应用中。
		%%: 输出一个”%”字符
		%F: 输出日志消息产生时所在的文件名称
		%L: 输出代码中的行号
		%m: 输出代码中指定的消息,产生的日志具体信息
		%n: 输出一个回车换行符，Windows平台为”\r\n”，Unix平台为”\n”输出日志信息换行
		可以在%与模式字符之间加上修饰符来控制其最小宽度、最大宽度、和文本的对齐方式。如：
		1)%20c：指定输出category的名称，最小的宽度是20，如果category的名称小于20的话，默认的情况下右对齐。
		2)%-20c:指定输出category的名称，最小的宽度是20，如果category的名称小于20的话，”-”号指定左对齐。
		3)%.30c:指定输出category的名称，最大的宽度是30，如果category的名称大于30的话，就会将左边多出的字符截掉，但小于30的话也不会有空格。
		4)%20.30c:如果category的名称小于20就补空格，并且右对齐，如果其名称长于30字符，就从左边交远销出的字符截掉。
		
3.在代码中使用Log4j
	
	1>得到记录器:
		public static Logger getLogger( String name)
		private static Logger logger = Logger.getLogger ( ServerWithLog4j.class.getName () )
	2>读取配置文件,配置Log4j环境
		BasicConfigurator.configure ()： 自动快速地使用缺省Log4j环境。  
		PropertyConfigurator.configure ( String configFilename) ：读取使用Java的特性文件编写的配置文件。  
		DOMConfigurator.configure ( String filename ) ：读取XML形式的配置文件。
	3>插入记录信息（格式化日志信息）
		Logger.debug ( Object message ) ;  
		Logger.info ( Object message ) ;  
		Logger.warn ( Object message ) ;  
		Logger.error ( Object message ) ;
		
		
二、Web项目中使用Log4j实例

	1、web应用的log4j使用基本上都采用：
	新建一个servlet，这个servlet在init函数中为log4j执行配置。一般就是读入配置文件。所以需要在web.xml中为这个servlet配置，同时设定load-on-startup为1。

	2、这个servlet配置log4j就是读出配置文件，然后调用configure函数。这里有两个问题：一、需要知道文件在哪里；二、需要正确的文件类型
	
	3、配置文件位置在web.xml中配置一个param即可，路径一般是相对于web的root目录
	
	4、文件类型一般有两种，一个是Java的property文件，另一种是xml文件
	
	
三、spring项目的log4j使用

	参考：http://www.codeceo.com/article/log4j-usage.html
			
	



