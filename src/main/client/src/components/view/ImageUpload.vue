<template>
  <div>
    <h3>{{ msg }}</h3>
    <br>
    <input ref="fileInput" type="file" accept="image/*" @input="selectImage">
    <button type="button" @click="imageUpload" class="btn atosButton">Upload</button>
    <p id="uploadText">{{ imageStatus }}</p>
    <img width="320" height="240" :src="previewImage"/>
  </div>
</template>

<script>
import Vue from 'vue';
import axios from 'axios';
import VueAxios from 'vue-axios';

Vue.use(VueAxios, axios);

export default {
  name: 'HomePage',
  data() {
    return {
      msg: null, image: null, previewImage: null, imageStatus: null,
    };
  },
  mounted() {
    Vue.axios.get('http://localhost:8081/').then((res) => {
      this.msg = res.data;
      this.imageStatus = 'Please select an image to upload.';
    });
  },
  methods: {
    imageUpload() {
      this.image = this.previewImage;
      this.previewImage = null;
      this.imageStatus = 'Image Uploaded!';
    },
    selectImage() {
      this.imageStatus = 'Image Preview: ';
      const input = this.$refs.fileInput;
      const file = input.files;
      console.log('file: ', file[0]);
      if (file && file[0]) {
        if (file[0].type.split('/')[0] !== 'image') {
          this.msg = 'This file format is not accepted.';
        }
        const reader = new FileReader();
        reader.onload = ((e) => {
          this.previewImage = e.target.result;
        });
        reader.readAsDataURL(file[0]);
      }
    },
  },
};

</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
.atosButton{
  background-color: rgb(3, 102, 161);
  color: white;
  border: none;
  text-align: center;
  padding: 5px;
}
*{
 font-size: 100%;
 font-family: Georgia;
}
</style>
