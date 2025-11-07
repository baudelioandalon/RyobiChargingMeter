#include <Arduino.h>
#include <WiFi.h>

// put function declarations here:
// int myFunction(int, int);
#define LED_BUILTIN 12
const char *ssid = "Bau";
const char *password = "yeyo132A";
int retryStatus = 0;

void setup()
{
  // Set WiFi to station mode and disconnect from an AP if it was previously connected.
  Serial.begin(115200);

  WiFi.mode(WIFI_STA);
  WiFi.disconnect();
  //    delay(100);
  Serial.println("Setup done");
}

void loop()
{
  // put your main code here, to run repeatedly:
  retryStatus = 0;

  Serial.println("Scan start");

  // WiFi.scanNetworks will return the number of networks found.
  int n = WiFi.scanNetworks();
  Serial.println("Scan done");
  while (WiFi.status() == WL_CONNECTED)
  {
    Serial.println("Connected to WiFi");
    Serial.println(WiFi.localIP());
    Serial.println("WiFi Name: " + WiFi.SSID() + " Wifi Address: " + WiFi.macAddress());
    Serial.println("RSSI: " + String(WiFi.RSSI()) + " dBm -> Channel: " + String(WiFi.channel()));
    Serial.println("Strength: " + String(WiFi.RSSI() + 100) + " %");
    delay(5000);
    /* code */
  }

  if (n == 0)
  {
    Serial.println("no networks found");
  }else{
    Serial.print(n);
    Serial.println(" networks found");
    Serial.println("Nr | SSID                             | RSSI | CH | Encryption");
    for (int i = 0; i < n; ++i)
    {
      // Print SSID and RSSI for each network found
      Serial.printf("%2d", i + 1);
      Serial.print(" | ");
      Serial.printf("%-32.32s", WiFi.SSID(i).c_str());
      Serial.print(" | ");
      Serial.printf("%4d", WiFi.RSSI(i));
      Serial.print(" | ");
      Serial.printf("%2d", WiFi.channel(i));
      Serial.print(" | ");
      Serial.print("\n");
      if (WiFi.SSID(i).equals(ssid))
      {
        Serial.print("Connecting to WiFi ..");
        WiFi.begin(ssid, password);
        while (WiFi.status() != WL_CONNECTED)
        {
          Serial.print('.');
          delay(1000);
          if (retryStatus > 10)
          {
            break;
          }         
          retryStatus++;
        }
        Serial.println("\nConnected to WiFi");
        Serial.println(WiFi.localIP());
        break;
      }
      delay(10);
    }
  }
  // Delete the scan result to free memory for code below.
  WiFi.scanDelete();
  // Wait a bit before scanning again.
  delay(5000);
}

// put function definitions here:
// int myFunction(int x, int y) {
//  return x + y;
//}