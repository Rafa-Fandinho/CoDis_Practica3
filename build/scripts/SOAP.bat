@rem
@rem Copyright 2015 the original author or authors.
@rem
@rem Licensed under the Apache License, Version 2.0 (the "License");
@rem you may not use this file except in compliance with the License.
@rem You may obtain a copy of the License at
@rem
@rem      https://www.apache.org/licenses/LICENSE-2.0
@rem
@rem Unless required by applicable law or agreed to in writing, software
@rem distributed under the License is distributed on an "AS IS" BASIS,
@rem WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@rem See the License for the specific language governing permissions and
@rem limitations under the License.
@rem
@rem SPDX-License-Identifier: Apache-2.0
@rem

@if "%DEBUG%"=="" @echo off
@rem ##########################################################################
@rem
@rem  SOAP startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%"=="" set DIRNAME=.
@rem This is normally unused
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Resolve any "." and ".." in APP_HOME to make it shorter.
for %%i in ("%APP_HOME%") do set APP_HOME=%%~fi

@rem Add default JVM options here. You can also use JAVA_OPTS and SOAP_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if %ERRORLEVEL% equ 0 goto execute

echo. 1>&2
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH. 1>&2
echo. 1>&2
echo Please set the JAVA_HOME variable in your environment to match the 1>&2
echo location of your Java installation. 1>&2

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto execute

echo. 1>&2
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME% 1>&2
echo. 1>&2
echo Please set the JAVA_HOME variable in your environment to match the 1>&2
echo location of your Java installation. 1>&2

goto fail

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\SOAP-1.0-SNAPSHOT.jar;%APP_HOME%\lib\cxf-rt-frontend-jaxws-3.3.5.jar;%APP_HOME%\lib\cxf-rt-transports-http-jetty-3.3.5.jar;%APP_HOME%\lib\jakarta.activation-1.2.2.jar;%APP_HOME%\lib\xml-resolver-1.2.jar;%APP_HOME%\lib\cxf-rt-frontend-simple-3.3.5.jar;%APP_HOME%\lib\cxf-rt-ws-addr-3.3.5.jar;%APP_HOME%\lib\cxf-rt-bindings-soap-3.3.5.jar;%APP_HOME%\lib\cxf-rt-databinding-jaxb-3.3.5.jar;%APP_HOME%\lib\cxf-rt-wsdl-3.3.5.jar;%APP_HOME%\lib\asm-7.1.jar;%APP_HOME%\lib\cxf-rt-bindings-xml-3.3.5.jar;%APP_HOME%\lib\cxf-rt-transports-http-3.3.5.jar;%APP_HOME%\lib\cxf-rt-ws-policy-3.3.5.jar;%APP_HOME%\lib\cxf-core-3.3.5.jar;%APP_HOME%\lib\jetty-security-9.4.24.v20191120.jar;%APP_HOME%\lib\jetty-server-9.4.24.v20191120.jar;%APP_HOME%\lib\jetty-http-9.4.24.v20191120.jar;%APP_HOME%\lib\jetty-io-9.4.24.v20191120.jar;%APP_HOME%\lib\jetty-util-9.4.24.v20191120.jar;%APP_HOME%\lib\jetty-continuation-9.4.24.v20191120.jar;%APP_HOME%\lib\slf4j-api-1.7.29.jar;%APP_HOME%\lib\jaxws-tools-2.3.2.jar;%APP_HOME%\lib\jaxws-eclipselink-plugin-2.3.2.jar;%APP_HOME%\lib\sdo-eclipselink-plugin-2.3.2.jar;%APP_HOME%\lib\jaxws-rt-2.3.2.jar;%APP_HOME%\lib\release-documentation-2.3.2-docbook.zip;%APP_HOME%\lib\samples-2.3.2.zip;%APP_HOME%\lib\jakarta.xml.ws-api-2.3.2.jar;%APP_HOME%\lib\jakarta.annotation-api-1.3.4.jar;%APP_HOME%\lib\jaxb-runtime-2.3.2.jar;%APP_HOME%\lib\streambuffer-1.5.7.jar;%APP_HOME%\lib\saaj-impl-1.5.1.jar;%APP_HOME%\lib\stax-ex-1.8.1.jar;%APP_HOME%\lib\jakarta.xml.bind-api-2.3.2.jar;%APP_HOME%\lib\jakarta.xml.soap-api-1.4.1.jar;%APP_HOME%\lib\jakarta.jws-api-1.1.1.jar;%APP_HOME%\lib\woodstox-core-5.1.0.jar;%APP_HOME%\lib\xmlschema-core-2.2.5.jar;%APP_HOME%\lib\javax.servlet-api-3.1.0.jar;%APP_HOME%\lib\policy-2.7.6.jar;%APP_HOME%\lib\gmbal-4.0.0.jar;%APP_HOME%\lib\mimepull-1.9.11.jar;%APP_HOME%\lib\FastInfoset-1.2.16.jar;%APP_HOME%\lib\ha-api-3.1.12.jar;%APP_HOME%\lib\stax2-api-4.1.jar;%APP_HOME%\lib\istack-commons-runtime-3.0.8.jar;%APP_HOME%\lib\jakarta.activation-api-1.2.1.jar;%APP_HOME%\lib\jaxb-xjc-2.3.2.jar;%APP_HOME%\lib\jaxb-jxc-2.3.2.jar;%APP_HOME%\lib\jakarta.mail-api-1.6.3.jar;%APP_HOME%\lib\jakarta.persistence-api-2.2.2.jar;%APP_HOME%\lib\org.eclipse.persistence.sdo-2.7.4.jar;%APP_HOME%\lib\org.eclipse.persistence.moxy-2.7.4.jar;%APP_HOME%\lib\commonj.sdo-2.1.1.jar;%APP_HOME%\lib\txw2-2.3.2.jar;%APP_HOME%\lib\wsdl4j-1.6.3.jar;%APP_HOME%\lib\neethi-3.1.1.jar;%APP_HOME%\lib\management-api-3.2.1.jar;%APP_HOME%\lib\pfl-tf-tools-4.0.1.jar;%APP_HOME%\lib\pfl-basic-tools-4.0.1.jar;%APP_HOME%\lib\pfl-tf-4.0.1.jar;%APP_HOME%\lib\pfl-dynamic-4.0.1.jar;%APP_HOME%\lib\pfl-basic-4.0.1.jar;%APP_HOME%\lib\org.eclipse.persistence.core-2.7.4.jar;%APP_HOME%\lib\pfl-asm-4.0.1.jar;%APP_HOME%\lib\org.eclipse.persistence.asm-2.7.4.jar


@rem Execute SOAP
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %SOAP_OPTS%  -classpath "%CLASSPATH%" gal.usc.etse.soap.Server %*

:end
@rem End local scope for the variables with windows NT shell
if %ERRORLEVEL% equ 0 goto mainEnd

:fail
rem Set variable SOAP_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
set EXIT_CODE=%ERRORLEVEL%
if %EXIT_CODE% equ 0 set EXIT_CODE=1
if not ""=="%SOAP_EXIT_CONSOLE%" exit %EXIT_CODE%
exit /b %EXIT_CODE%

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
