grails.servlet.version = "3.0" // Change depending on target container compliance (2.5 or 3.0)
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.work.dir = "target/work"
grails.project.target.level = 1.6
grails.project.source.level = 1.6
//grails.project.war.file = "target/${appName}-${appVersion}.war"


grails.project.fork = [
    // configure settings for compilation JVM, note that if you alter the Groovy version forked compilation is required
    //  compile: [maxMemory: 256, minMemory: 64, debug: false, maxPerm: 256, daemon:true],

    // configure settings for the test-app JVM, uses the daemon by default
    test: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, daemon:true],
    // configure settings for the run-app JVM
    run: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, forkReserve:false],
    // configure settings for the run-war JVM
    war: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, forkReserve:false],
    // configure settings for the Console UI JVM
    console: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256]
]

grails.project.dependency.resolver = "maven" // or ivy
grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // specify dependency exclusions here; for example, uncomment this to disable ehcache:
        // excludes 'ehcache'
    }
    log "error" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    checksums true // Whether to verify checksums on resolve
    legacyResolve false // whether to do a secondary resolve on plugin installation, not advised and here for backwards compatibility

    repositories {
        inherits true // Whether to inherit repository definitions from plugins

        grailsPlugins()
        grailsHome()
        mavenLocal()
        grailsCentral()
        mavenCentral()
        // uncomment these (or add new ones) to enable remote dependency resolution from public Maven repositories
        //mavenRepo "http://repository.codehaus.org"
        //mavenRepo "http://download.java.net/maven/2/"
        //mavenRepo "http://repository.jboss.com/maven2/"
    }

    dependencies {
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes e.g.
        // runtime 'mysql:mysql-connector-java:5.1.24'

        test 'com.google.guava:guava:15.0'
        test 'net.sourceforge.htmlunit:htmlunit:2.13'

        //		compile 'xml-apis:xml-apis:1.4.01'
        //		test 'xml-apis:xml-apis:1.4.01'
        //		build 'xml-apis:xml-apis:1.4.01'

        test 'junit:junit:4.11'
        test 'org.hamcrest:hamcrest-all:1.3'
        test "org.spockframework:spock-grails-support:0.7-groovy-2.0"
        test "com.moonillusions.htmlUnitMatchers:htmlUnitMatchers:0.1"
        //test 'net.sourceforge.htmlunit:htmlunit:2.13'
		compile "org.jadira.usertype:usertype.jodatime:2.0"
        compile 'com.moonillusions.utils:PropertyNavigation:1.0-SNAPSHOT'
    }

    plugins {
        // plugins for the build system only
        build ':tomcat:7.0.54'

        // plugins for the compile step
        compile ':scaffolding:2.1.2'

        // Using 1.1.6 as a solution for https://jira.grails.org/browse/GRAILS-11535
        compile ':cache:1.1.6'
        compile ':asset-pipeline:1.8.11'

        // plugins needed at runtime but not for compilation
        runtime ':hibernate4:4.3.5.4'
        runtime ':database-migration:1.4.0'
        runtime ':jquery:1.11.1'

        // Uncomment these (or add new ones) to enable additional resources capabilities
        //runtime ":zipped-resources:1.0.1"
        //runtime ":cached-resources:1.1"
        //runtime ":yui-minify-resources:0.1.5"

        compile ":build-test-data:2.1.2"
        compile ":fixtures:1.3"
        compile ":joda-time:1.5"
        test ":fields:1.4"
    }
}
