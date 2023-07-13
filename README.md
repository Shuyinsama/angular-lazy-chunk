## Angular Lazy Chunk Reproduction repository

This repository is to demonstrate an issue we have with implementing Angular Lazy Loading (https://angular.io/guide/lazy-loading-ngmodules)

When the build files of angular are served by another server (in this case Spring Boot), the lazy loading does not work since it
tries to load the chunk files from a different base href path.

The angular app is also multilingual and outputs multiple dist folders for each language.

### How to run

1. You will need java installed
2. In the project root directory run the following command `./gradlew clean build`
3. The project will build both the backend and frontend. It also copies over the `dist` files from the frontend to the `static` directory used by Spring Boot.

There are 2 states in which the application can run:
1. Spring Boot in `development` mode by using the `dev` profile. The application will expect the frontend to be served by `ng serve`
2. In `production` mode where the Spring Boot application will load the angular build assets from the `static` directory.

### Run in development mode
1. Run the backend with the following command `./gradlew bootRun --args='--spring.profiles.active=dev'`
2. Run the frontend from the `frontend` directory with `.gradle/nodejs/node-v18.12.0-darwin-x64/bin/node .gradle/nodejs/node-v18.12.0-darwin-x64/bin/npm run start` to run with the included node version. Or use `npm run start`
3. Open the application at `http://localhost:8081/en`

### Run in production mode
1. Run the backend with the following command `./gradlew bootRun`
2. Open the application at `http://localhost:8081/en`
