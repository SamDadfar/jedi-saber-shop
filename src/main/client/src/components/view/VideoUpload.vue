<template>
  <div>
    <h3>{{ msg }}</h3>
    <br>
    <input ref="fileInput" type="file" accept="video/*" @input="selectVideo">
    <button type="button" @click="videoUpload" class="btn atosButton">Upload</button>
    <p id="uploadText">{{ videoStatus }}</p>
    <video width="320" height="240" controls :class="videoClass">
        <source id="videoSource" v-if="previewVideo" :src="previewVideo" :type="videoType">
        Your browser does not support the video tag.
    </video>
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
      msg: null,
      video: null,
      previewVideo: null,
      videoType: 'video/mp4',
      videoStatus: 'Please select a video to upload.',
      videoClass: 'hidden',
    };
  },
  mounted() {
    Vue.axios.get('http://localhost:8081/').then((res) => {
      this.msg = res.data;
    });
  },
  methods: {
    videoUpload() {
      this.video = this.previewVideo;
      this.previewVideo = null;
      this.videoClass += 'hidden';
      this.videoStatus = 'Video Uploaded!';
    },
    selectVideo() {
      this.videoStatus = 'Video Preview: ';
      const input = this.$refs.fileInput;
      const file = input.files;
      console.log('file: ', file[0]);
      if (file && file[0]) {
        this.videoType = file[0].type;
        if (this.videoType.split('/')[0] !== 'video') {
          this.msg = 'This file format is not accepted.';
        }
        this.videoClass = this.videoClass.replace('hidden', '');
        const reader = new FileReader();
        reader.onload = ((e) => {
          this.previewVideo = e.target.result;
          console.log('The reader has been opened and a preview should be showing');
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
.hidden{
  display: none;
}
</style>
