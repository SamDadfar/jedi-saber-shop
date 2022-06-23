<template>
  <div id="content" class="content">
    <!-- <h3>{{ msg }}</h3>
    <br>
    <router-link to="/getModel"><p>Get Model</p></router-link>
    <a @click="changeText()"><p>{{ tstGet }}</p></a>
    <br> -->
    <section class="bg-image d-flex justify-content-center align-items-center text-black" style="background-image: url('https://mdbcdn.b-cdn.net/img/Photos/Others/images/76.jpg'); ">
      <!-- Card Container -->
      <mdb-container>
        <mdb-row>
          <mdb-col md="8" lg="7" class="mx-auto float-none white z-depth-1 py-2 px-2">
            <mdb-card-body>
              <h2 class="h2-responsive"><strong>  <h1>{{ msg }}</h1></strong></h2>
              <p class="pb-4">Competence page</p>
              <div class="content">
                <h3>{{ msg }}</h3>
              </div>
            </mdb-card-body>
          </mdb-col>
        </mdb-row>
      </mdb-container>
    </section>
    <section>
      <div class="content">
        <h5 class="contentTitle">Upcoming Events </h5>
        <div class="containerT">No upcoming events :(</div>
      </div>
      <div class="content">
        <h5 class="contentTitle">Posts: </h5>
        <div class="containerT">No posts :(</div>
      </div>
      <button @click="edit = true;" class="btn atosButton" type="button">
      New Post</button>
      <template v-if="edit === true">
        <div class="content">
          <h5 class="contentTitle">New Post </h5>
          <div style="text-align: left; padding: 2px; border: 2px rgb(3, 102, 161) solid;">
            <p style="display: inline-block; vertical-align: baseline;">Title: </p>
            <div style="width:200px; vertical-align: middle;" contenteditable="true"
            class="textEditor" ref="postTitle"></div>
            <div contenteditable="true"></div>
            <p style="display: inline-block; margin-right: 2px;">
            Content: </p>
            <div id="editor" contenteditable="true" class="textEditor" ref="postContent">
            </div>
            <p style="margin-right: 2px;">
            Images & Videos: </p>
            <div v-if="videos.size !== 0 | images.size !== 0">
              <img width="320" height="240" :src="image"
              v-for="image in images" v-bind:key="image"/>
              <video width="320" height="240" controls v-for="(type, video) in videos"
              v-bind:key="video">
                <source :src="video" :type="type">
                Your browser does not support the video tag.
              </video>
            </div>
          </div>
        </div>
        <button class="btn atosButton" type="button"
        @click="media = 'image'; statusUpload = 'Please select an image to upload.';"
        >Attach Image</button>
        <button class="btn atosButton" type="button"
        @click="media = 'video'; statusUpload = 'Please select a video to upload.';"
        >Attach Video</button>
        <button @click="savePost();" class="btn atosButton" type="button">
        Upload Post</button>
      </template>
      <template v-if="media !== null">
        <div>
          <!-- Loads the correct file type input -->
          <template  v-if="media === 'image'">
            <input ref="fileInput" type="file" accept="image/*" @input="selectImgVid"
            key="input-image">
          </template>
          <template  v-else-if="media === 'video'">
            <input ref="fileInput" type="file" accept="video/*" @input="selectImgVid"
            key="input-video">
          </template>
          <button type="button" @click="uploadImgVid" class="btn atosButton">
          Attach</button>
          <p>{{ statusUpload }}</p>
          <!-- Loads the correct preview object -->
          <template  v-if="media === 'image'">
            <img width="320" height="240" :src="previewUpload" :class="previewClass"/>
          </template>
          <template  v-else-if="media === 'video'">
            <video v-if="previewUpload" width="320" height="240" controls
              :class="previewClass">
              <source :src="previewUpload" :type="videoType">
              Your browser does not support the video tag.
            </video>
          </template>
        </div>
      </template>
    </section>
  </div>
</template>

<script>
import Vue from 'vue';
import axios from 'axios';
import VueAxios from 'vue-axios';
import {
  mdbContainer, mdbRow, mdbCol, mdbCardBody,
// eslint-disable-next-line import/no-unresolved
} from 'mdbvue';
import backendApi from '../backend-api';

Vue.use(VueAxios, axios);

export default {
  name: 'HomePage',
  data() {
    return {
      msg: null,
      tstGet: 'Get Model with href call',
      videoType: 'video/mp4',
      media: null,
      previewUpload: null,
      previewClass: 'hidden',
      fileUpload: null,
      statusUpload: null,
      fileType: null,
      edit: false,
      images: [],
      videos: {},
    };
  },
  created() {
    this.getMessage();
  },
  watch: {
    $route: 'getMessage',
  },
  components: {
    mdbContainer,
    mdbRow,
    mdbCol,
    mdbCardBody,
  },
  methods: {
    changeText() {
      backendApi.getModel().then((response) => {
        console.log(response.data.name);
        this.tstGet = response.data.name;
      });
    },
    savePost() {
      console.log('title: ', this.$refs.postTitle.innerHTML);
      console.log('content: ', this.$refs.postContent.innerHTML);
      console.log('image count: ', this.images.length);
      console.log('video count: ', Object.keys(this.videos).length);
      const data = {
        title: this.$refs.postTitle.textContent,
        content: this.$refs.postContent.textContent,
        images: this.images,
        videos: this.videos,
      };
      console.log(data);
      this.edit = false;
      // backendApi.sendCompetencePost(data)
      //   .then((respones) => {
      //     console.log(respones);
      //   });
    },
    getMessage() {
      console.log(this.$route.params.id);
      switch (this.$route.params.id) {
        case '1':
          this.msg = 'Java';
          break;
        case '2':
          this.msg = '.Net';
          break;
        case '3':
          this.msg = 'Business Analyse';
          break;
        default:
          this.msg = 'Couldn\'t get the competence.';
          break;
      }
    },
    uploadImgVid() {
      if (this.previewUpload == null) {
        console.log('No file was selected');
        return;
      }
      this.fileUpload = this.previewUpload;
      this.previewClass += 'hidden';
      if (this.media === 'image') {
        this.statusUpload = 'Image Uploaded!';
        this.images.push(this.previewUpload);
      } else if (this.media === 'video') {
        this.statusUpload = 'Video Uploaded!';
        this.videos[this.previewUpload] = this.videoType;
        console.log('videos: ', this.videos[this.previewUpload]);
      }
      this.previewUpload = null;
      this.media = null;
    },
    selectImgVid() {
      if (this.media === 'image') {
        this.statusUpload = 'Image  Preview: ';
      } else if (this.media === 'video') {
        this.statusUpload = 'Video  Preview: ';
      }
      const input = this.$refs.fileInput;
      const file = input.files;
      console.log('file: ', file[0]);
      console.log('file type expected: ', this.media);
      if (file && file[0]) {
        if (this.media === 'image' && file[0].type.split('/')[0] !== 'image') {
          this.msg = 'This image format is not accepted.';
        } else if (this.media === 'video' && file[0].type.split('/')[0] !== 'video') {
          this.msg = 'This video format is not accepted.';
        } else if (this.media === 'video') {
          this.videoType = file[0].type;
        }
        this.previewClass = this.previewClass.replace('hidden', '');
        const reader = new FileReader();
        reader.onload = ((e) => {
          this.previewUpload = e.target.result;
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
  margin: 5px;
}
*{
 font-family: Georgia;
}
.hidden{
  display: none;
}
.content{
  text-align: center;
  padding: 5px;
  width: 80%;
  margin: 0 auto;
}
.contentTitle{
  color: rgb(3, 102, 161);
  text-align: center;
  width: 80%;
  margin: 0 auto;
}
.containerT{
  border-top: 5px rgb(3, 102, 161) solid;
  width: 80%;
  text-align: center;
  /* display: flex;
  justify-content: center; */
  /* border-top-color: rgb(3, 102, 161); */
  margin: 0 auto;
  margin-bottom: 10px;
  margin-top: 10px;
}
.textEditor{
  margin: 0 auto;
  border: 2px rgb(3, 102, 161) solid;
  display: inline-block;
  width: 100%;
  overflow-y: auto;
  background: white;
  padding: 3px;
  max-width: 100%;
  overflow-wrap: break-word;
}

</style>
