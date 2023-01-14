This is a project with API tests sample using RestAssured and TestNG.

Test cases:

**Case 1.**

GIVEN a valid search page URL 'https://www.vodafone.ua/api/search/'

WHEN a user sends GET request to the specified URL by adding a relevant String keyword (Latin or Cyrillic) to the end: 
https://www.vodafone.ua/api/search/{keyword}

THEN the request is performed successfully with status code 200

AND content type is JSON

AND the response body contains the specified keyword in 'title' or 'short_description' in any data item


**Case 2.**

GIVEN a valid search page URL 'https://www.vodafone.ua/api/search/'

WHEN a user sends GET request to the specified URL by adding a single character (Latin or Cyrillic) to the end:
https://www.vodafone.ua/api/search/{single_char}

THEN the request is performed successfully with status code 200

AND an empty list of data items is returned


**Case 3.**

GIVEN a valid search page URL 'https://www.vodafone.ua/api/search/'

WHEN a user send GET request to the specified URL without a keyword or with any number of spaces instead:
https://www.vodafone.ua/api/search/{}

THEN the request is performed unsuccessfully with status code 404

**Case 4.**

GIVEN a valid search page URL 'https://www.vodafone.ua/api/search/'

WHEN a user sends GET request to the specified URL by adding a senseless String keyword with random characters:
https://www.vodafone.ua/api/search/{abracadabra}

THEN the request is performed successfully with status code 200

AND an empty list of data is returned


**Case 5.**

GIVEN an invalid search page URL 'https://www.vodafone.ua/api/searchh'

WHEN a user sends GET request using the given URL

THEN the request is performed unsuccessfully with status code 404