# Country-List-App
REST countries sample app that loads information from [REST countries
API V3](https://restcountries.com/#api-endpoints-v3-all) 

## Tech Stack
- ![kotlin]
- ![retrofit]

Used simple API `https://restcountries.com/v3/all` which returns data in JSON form.
Retrofit was used to fetch JSON data to the App into model class which holds the data as parcelable object
The app is developed using the MVVM pattern

class CountryApiClient fetch the data from the remote api and saves the response as live data
class CountryRepository gets the data from CountryApiClient
class CountryViewModel is the viewmodel class which gets data from CountryRepository so as to update views with data

a searchview is implemented to query for any country from the data.

##Appetizer_Link 
https://appetize.io/app/5cnyzwdd7kxzbgdmczv62vaiqq?device=pixel4&osVersion=11.0&scale=75



