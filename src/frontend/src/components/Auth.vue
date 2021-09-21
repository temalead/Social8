<template>
  <v-container class="d-flex flex-column">
    <v-row class="align-center justify-center">
      <div class="small-logo-container">LOGO</div>
    </v-row>
    <v-row>
      <v-col class="ml-4 mr-4">
        <form method="post">
          <input type="hidden" name="_token" :value="csrf" />
          <v-text-field
            v-model="login"
            :error-messages="loginErrors"
            :counter="30"
            label="Phone number or email"
            required
            @input="$v.login.$touch()"
            @blur="$v.login.$touch()"
          ></v-text-field>
          <v-text-field
            v-model="password"
            :error-messages="passwordErrors"
            label="Password"
            required
            @input="$v.password.$touch()"
            @blur="$v.password.$touch()"
            class="mb-2"
          ></v-text-field>
          <v-row>
            <v-btn
              class="mt-9 button"
              color="#f6f6f6"
              width="100%"
              @click="submit"
              disabled
            >
              LOG IN
            </v-btn>
          </v-row>
          <v-row>
            <v-btn
              class="button second"
              color="#f6f6f6"
              width="100%"
              @click="registrationPageRout"
            >
              create new account
            </v-btn>
          </v-row>
          <v-row class="d-flex justify-center">
            <v-btn class="button second text-caption" color="error" text
              >Forgot Password?
            </v-btn>
          </v-row>
        </form>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { validationMixin } from "vuelidate";
import { required, maxLength, minLength } from "vuelidate/lib/validators";
import router from "../router";

export default {
  name: "Main",
  mixins: [validationMixin],
  validations: {
    login: {
      required,
      valid: function (value) {
        const isEmail = /.+@.+\..+/.test(value);
        const isPhoneNumber = /^[0-9]{10}$/.test(value);
        return isEmail || isPhoneNumber;
      },
      maxLength: maxLength(50),
    },
    password: {
      required,
      valid: function (value) {
        // const containsUppercase = /[A-Z]/.test(value);
        // const containsLowercase = /[a-z]/.test(value);
        const containsNumber = /[0-9]/.test(value);
        const containsSpecial = /[#?!@$%^&*-]/.test(value);
        const constainsDoubleAp = /["]/.test(value);
        return containsNumber && containsSpecial && !constainsDoubleAp;
      },
      minLength: minLength(10),
      maxLength: maxLength(30),
    },
  },
  data: () => ({
    login: "",
    password: "",
    csrf: null,
  }),

  computed: {
    loginErrors() {
      const errors = [];
      let login = this.$v.login;
      if (!login.$dirty) return errors;
      !login.maxLength &&
        errors.push("Login must be at most 50 characters long");
      !login.required && errors.push("Login is required.");
      if (!login.valid) errors.push("Login must be valid");
      return errors;
    },
    passwordErrors() {
      const errors = [];
      let pass = this.$v.password;
      if (!pass.$dirty) return errors;
      if (!pass.valid)
        errors.push(
          "Password must include numbers and special symbols(#?!@$%^&*-)"
        );
      if (!pass.minLength || !pass.maxLength)
        errors.push("Password must be between 10 and 25 symbols");
      !pass.required && errors.push("Password is required");
      return errors;
    },
  },

  methods: {
    submit() {
      this.$v.$touch();
    },
    registrationPageRout: function () {
      router.push({ name: "Registration" });
    },
  },
};
</script>

<style scoped>
.button {
  border-radius: 16px;
}
.second {
  margin-top: 12px;
}
.small-logo-container {
  height: 55vh;
}
/* .logo {
  position: absolute;
  width: 104px;
  height: 104px;
  left: 128px;
  top: 260px;
  background: url(image.png);
}

.image {
  position: absolute;
  left: 0%;
  right: 0%;
  top: 0%;
  bottom: 0%;
} */
</style>