<template>
  <v-container class="d-flex flex-column">
    <v-row class="align-center justify-center">
      <div class="small-logo-container">LOGO</div>
    </v-row>
    <v-row>
      <v-col class="ml-4 mr-4 px-0">
        <form method="post">
          <input type="hidden" name="_token" :value="csrf" />

          <v-text-field
            v-model="login"
            :error-messages="loginErrors"
            :counter="50"
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
          <v-btn
            class="px-0 button second"
            color="#f6f6f6"
            width="100%"
            @click="submit"
          >
            create new account
          </v-btn>
        </form>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { validationMixin } from "vuelidate";
import { required, maxLength, minLength } from "vuelidate/lib/validators";

export default {
  name: "Registration",
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
      fetch("http://localhost:8080/registration", {
        method: "POST",
        // headers: {
        //   "Content-Type": "application/json",
        // },
        body: { login: this.login, password: this.password },
      });
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
</style>