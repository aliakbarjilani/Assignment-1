package models;

public class Fixtures {

	  public static User[] users =
		  {
		    new User ("714ddd6a-0303-4e1c-b569-d09675624424", "marge", "simpson", "marge@simpson.com",  "secret"),
		    new User ("ed3099b1-0024-42f3-88a7-840cce4f69a8", "lisa",  "simpson", "lisa@simpson.com",   "secret"),
		    new User ("6f8de436-cae8-4874-bf77-59c5283ac864", "bart",  "simpson", "bart@simpson.com",   "secret"),
		    new User ("69e61e0e-483e-49b3-a28f-1fb4fc37771b", "maggie","simpson", "maggie@simpson.com", "secret"),
		    new User ("1dc85eb2-8dc3-42f0-ad30-8b324ae059ca", "lina",  "simpson", "lsimpson@simpson.com", "secret") 
		  };

		  public static Activity[] activities =
		  {
		    new Activity ("28886e73-e628-4dce-8df0-4e66d2df3d94", "walk", "fridge", 15.0, "2017:11:04.04:10:10", 18305),//, "[Location{0, 23.5, 32.5}, Location{1, 12.5, 34.5}]"),
		    new Activity ("ec3da6db-24c0-4b62-bf5f-e44fc88369ac", "fly",  "Dublin", 10.0, "2017:11:03.04:10:10", 135),// "[]"),
		    new Activity ("fea6bcc9-d16c-46fa-a2c8-51d8a40a28b7", "run",  "work",   10.0, "2017:10:03.04:10:10", 58),// "[]"),
		    new Activity ("3afd4de3-5112-4a54-ade6-ad428fd79529", "cycle","school", 25.0, "2017:11:04.06:10:10", 999),// "[Location{0, 23.5, 32.5}, Location{1, 12.5, 34.5}]"),
		    new Activity ("85dea5a2-0eb1-4070-b0ed-c3b1fb64cc66", "run",  "forest", 18.9, "2017:08:09.04:05:03", 33),// "[Location{2, 23.5, 32.5}, Location{3, 12.5, 34.5}]")
		  };

		  public static Location[]locations =
		  {
		    new Location(23.5f, 32.5f),
		    new Location(12.4f, 34.2f),
		    new Location(75.3f, 39.8f),
		    new Location(44.4f, 27.6f)
		  };
}
