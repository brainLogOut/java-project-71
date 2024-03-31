run-dist:
	./build/install/app/bin/app

install-dist:
	gradle clean
	gradle installDist

build:
	./gradlew clean build

checkstyleMainTest:
	gradle checkstyleMain
	gradle checkstyleTest

say-hello:
	echo "Hello world"

.PHONY: build
