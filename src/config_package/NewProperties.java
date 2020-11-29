package config_package;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class NewProperties {
    private final Properties m_properties;

   public NewProperties(Properties properties){
       this.m_properties = properties;
   }

   public void load(InputStream inputStream) throws IOException {
       this.m_properties.load(inputStream);
   }

   public String getProperty(String key) throws IllegalArgumentException{
      String value = m_properties.getProperty(key);
      if (value == null){
          throw new IllegalArgumentException("properties value do not exist in file");
      }
      return value;
   }
}
