package utilities.internal.paypal;

public enum PayPalEnvironment {
   
   LIVE(""), SANDBOX("sandbox.");
   private final String urlModifier;
   
   PayPalEnvironment(String urlModifier) {
      this.urlModifier = urlModifier;
   }
   
   public String getUrlModifier() {
      return urlModifier;
   }
}
