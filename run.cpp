#include <iostream>
using namespace std;

int main()
{
    string cmd = "mvn clean install";
    system(cmd.c_str());
    cmd = "java -jar ./target/app-1.0.jar";
    system(cmd.c_str());
}