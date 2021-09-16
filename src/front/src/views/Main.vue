<template>
  <v-row class="align-center justify-center">
    <v-col cols="6">
      <form>
        <v-text-field
          v-model="login"
          :error-messages="loginErrors"
          :counter="15"
          label="Login"
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

        <v-btn class="mr-4" @click="submit"> Войти </v-btn>
        <v-btn @click="clear"> Очистить </v-btn>
      </form>
    </v-col>
  </v-row>
</template>

<script>
import { validationMixin } from "vuelidate";
import { required, maxLength, minLength } from "vuelidate/lib/validators";

export default {
  name: "Main",
  mixins: [validationMixin],
  validations: {
    login: { required, maxLength: maxLength(15) },
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
  }),

  computed: {
    loginErrors() {
      const errors = [];
      if (!this.$v.login.$dirty) return errors;
      !this.$v.login.maxLength &&
        errors.push("Login must be at most 15 characters long");
      !this.$v.login.required && errors.push("Login is required.");
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
    clear() {
      this.$v.$reset();
      this.name = "";
      this.email = "";
      this.select = null;
      this.checkbox = false;
    },
  },
};
</script>

<style></style>