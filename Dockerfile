FROM openjdk:17
ADD /target/booking-app-0.0.1-SNAPSHOT.jar booking.jar
ENTRYPOINT ["java", "-jar", "booking.jar"]