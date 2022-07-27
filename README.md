Interview Test App
======================

## Prerequisites
In order to run this project you need the following:
- Android Studio 4.1.0 or better
- Gradle 6.5 or better
- JDK 1.8
- [Android SDK](https://developer.android.com/studio/index.html)


# Architecture
This project is built using MVVM architecture pattern in context of Clean Architecture. So, there are three layers in the project:
- Domain: Which is responsible for basic logics and provides use cases and interfaces for repositories.
- Data: Which provides data needed for the app to function.
- Presentation: Which handles UI and presentation logic of the app.


## Data
We have repository and data source implementations.
Creating different models for data layers cause lots of boilerplate but we can develop tools to
generate that automatically. If we don't have time we could use entity models. In my experience
importing some android related SDKs to domain layer cause other careless import and make testing hard.
We could also control it with lints and merge request templates for review.

### Refactor Strategy:
We can set some guidelines in the beginning.
So later if we develop a tool for that we would migrate easily and without change to domain layer entities


# Technologies And Decisions
This project has been fully written in Kotlin with the use of following libraries:
- Architecture Components
- Hilt
- Navigation Component
- Flow
- Coroutines
- MockK
- Gson

In this section I'll try to explain reason behind some of my decisions
## Hilt
We Use hilt because of less boilerplate codes. Also, generally I think it's easier for integration tests as well.

**Create Hilt modules in the related gradle modules:**
This scenario could help us, if we want to reuse our gradle modules in different apps or
if we don't want to mock them in some integration tests.

**Create Hilt modules in the app module:**
This scenario is easier to test for component testing but it needs more boilerplate codes.


## Coroutine
For the threading and observing I could use technologies like Livedata with Thread pools, Rx and Coroutine.
It's hard to handle things like back pressure
or debounce with Livedata and ThreadPools.
On the other hand, Coroutine is lighter than Rx. It's native and somehow
it's easier to test because Google has created some libraries for that.


## Test
I wrote tests for the repository, useCases and dataSource.

I tried to use stubs. [Stubs do better in terms of state verification](https://martinfowler.com/articles/mocksArentStubs.html)
, but I added Mockk for Repository test and show how we can Leverage behavior verification tools in a good way.


## UX
I keep UI minimal and tried to show I know how to use things like constraint layout, styles, dimens drawables, etc, but I know from the user side it's not good at all.
I created one activity for the host and another screen implemented as a fragment
for navigating between screens I used the navigation library of android jetpack
