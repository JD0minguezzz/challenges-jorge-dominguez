# C# Challenge #


Create a solution with a library and test project.

.NET Core SDK 2.2 [Download](https://www.microsoft.com/net/download)

```shell
dotnet new classlib -n CSharpChallenge
dotnet new nunit -n CSharpChallenge.Test
dotnet add CSharpChallenge.Test\CSharpChallenge.Test.csproj reference CSharpChallenge\CSharpChallenge.csproj
dotnet add CSharpChallenge.Test\CSharpChallenge.Test.csproj package coverlet.msbuild

dotnet new sln -n CSharpChallenge
dotnet sln CSharpChallenge.sln add CSharpChallenge\CSharpChallenge.csproj
dotnet sln CSharpChallenge.sln add CSharpChallenge.Test\CSharpChallenge.Test.csproj
dotnet restore
dotnet build
```

## Visual Studio Code Extensions ##

* [.Net Core Test Explorer](https://marketplace.visualstudio.com/items?itemName=formulahendry.dotnet-test-explorer)
* [C#](https://marketplace.visualstudio.com/items?itemName=ms-vscode.csharp)
* [Coverage Gutter](https://marketplace.visualstudio.com/items?itemName=ryanluker.vscode-coverage-gutters)

## Code Coverage ##

```shell
dotnet test CSharpChallenge.Test\CSharpChallenge.Test.csproj /p:CollectCoverage=true /p:CoverletOutputFormat=\"opencover,lcov\" /p:CoverletOutput=../lcov
```

## Using C# 6 and 7 Challenge ##

Refactor the code using the new features

### C# 6 ###
* Null-conditional operator
* String Interpolation
* Expression-bodied Initialization
* using static

### C# 7 ###
* out Variables
* Tuples
* Pattern Matching

## Unit testing Challenge ##

Increase the code coverage of the library CSharpChallenge 