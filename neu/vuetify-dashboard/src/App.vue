<template>
  <v-app>
    <v-card>
      <v-card-title><h1 class="display-1">Steuerberechnung</h1> </v-card-title>
      <v-card-text>
        <v-form>
          <v-text-field type="number" label="Steuerbetrag"> </v-text-field>

          <v-container fluid>
            <v-row align="center">
              <v-col class="d-flex" cols="12" sm="6">
                <v-select :items="items" label="Land"></v-select>
              </v-col>
              <v-col class="d-flex" cols="12" sm="6">
                <v-select :items="items" label="Steuerart"></v-select>
              </v-col>
            </v-row>
          </v-container>
        </v-form>
      </v-card-text>
      <v-card-action>
        <v-btn color="success" v-on:click="makeGetRequest"
          >Betrag berechnen</v-btn
        >
      </v-card-action>
      <v-card-footer padless>
        <v-col class="text-center" cols="12">
          <strong>Endergebnis:</strong> — {{ info }}
        </v-col>
      </v-card-footer>
    </v-card>
  </v-app>
</template>

<script>
import axios from "axios";

export default {
  name: "App",

  components: {},

  data: () => ({
    items: ["Foo", "Bar", "Fizz", "Buzz"],
    jsonres: [],
    endresult: 30,
    info: {},
  }),

  methods: {
    async makeGetRequest() {
      // `this` inside methods points to the Vue instance
      this.name = "sdfsdf";
      this.endresult = 20;
      // `event` is the native DOM event
      const jsonres = await axios.get(
        "https://api.coindesk.com/v1/bpi/currentprice.json"
      );
      this.info = jsonres.data;
    },

    // hier alles für den Server abbilden!!!
    /*
  
		apiSubRouter.route("/v1/products*").handler(BodyHandler.create());
    //für alle Produkte
		apiSubRouter.get("/v1/products").handler(this::getAllProducts);
		apiSubRouter.get("/v1/products/:id").handler(this::getProductById);
		apiSubRouter.post("/v1/products").handler(this::addProduct);
		apiSubRouter.put("/v1/products/:id").handler(this::updateProductById);
		apiSubRouter.delete("/v1/products/:id").handler(this::deleteProductById);

		return apiSubRouter;
*/
  },
};
</script>