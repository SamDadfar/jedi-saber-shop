<template>
    <div id="calendar">
      <div id='filters'>
        <div id='divDropdown'>
          <multiselect id='dropdown' v-model="value" :options="allCompetences" :multiple="true"
          :close-on-select="false" :clear-on-select="false" :preserve-search="true"
          placeholder="Select competence(s)" label="competenceName" track-by="competenceName"
          :preselect-first="false" @input="onChange">
          </multiselect>
          <div id='divDateFilters'>
            <label for="">From:</label>
            <input type="date" v-model="startDate" @change="onDateChange">
            <label for="">To:</label>
            <input type="date" v-model="endDate" @change="onDateChange">
          </div>
        </div>
      </div>
      <ul id="ulCalendar">
        <li v-for="(competence, index) in selectedEvents"
        :key="index"
        :name="competence.competenceName" :description="competence.description"
        :start="competence.date + ' ' + competence.time" :duration="competence.duration"
        :end="competence.endDate + ' ' + competence.endTime"
        >
          <p id='competenceName'>
            {{ competence.competenceName }}
          </p>
          <span id='date'>
            {{competence.date}}
          </span>
          <span id='description'>
            {{competence.description}}
          </span>
          <b-button id='downloadCalendar' v-on:click="calendarDownload">
            <i class="fas fa-download"></i>
          </b-button>
        </li>
      </ul>
    </div>
</template>

<script>
import Vue from 'vue';
import axios from 'axios';
import VueAxios from 'vue-axios';
import Multiselect from 'vue-multiselect';
import { ics } from 'calendar-link';
import api from '../backend-api';

Vue.use(VueAxios, axios);

export default {
  name: 'Calendar',

  components: {
    Multiselect,
  },

  created() {
    this.competencesSortedByDate();
    this.competencesSortedByName();
  },

  data() {
    return {
      allEvents: [],
      allCompetences: [],
      competencesName: [],
      selectedEvents: [],
      responseCompetences: [],
      responseCompetences2: [],
      newItems: [],
      value: [],
      startDate: null,
      endDate: null,
    };
  },

  methods: {
    calendarDownload(value) {
      // Get the attributes from the parent
      const details = value.target.parentElement.attributes;
      // Create the calendar event object to download
      const calendarEvent = {
        title: details[1].value,
        description: details[2].value,
        start: details[3].value,
        end: details[4].value,
      };
      // Open the downloader
      window.open(ics(calendarEvent));
    },
    competencesSortedByDate() {
      this.responseCompetences = [];
      this.responseCompetences2 = [];
      api.getAllCompetences().response.forEach((element) => {
        this.responseCompetences.push(element);
        this.responseCompetences2.push(element);
      });
      this.selectedEvents = this.sortArraysOnDate(this.responseCompetences);
      this.selectedEvents = this.sortArraysOnDate(this.responseCompetences);
    },
    competencesSortedByName() {
      this.allEvents = this.sortArraysOnName(this.responseCompetences2);
      let check = true;
      this.allEvents.forEach((eventElement) => {
        check = true;
        this.allCompetences.forEach((element) => {
          if (element.competenceName === eventElement.competenceName) {
            check = false;
          }
        });
        if (check) {
          this.allCompetences.push(eventElement);
        }
      });
      this.value = this.allCompetences;
    },
    sortArraysOnDate(array) {
      return array.sort((a, b) => (a.date > b.date ? 1 : -1));
    },
    sortArraysOnName(array) {
      return array.sort((a, b) => (a.competenceName > b.competenceName ? 1 : -1));
    },
    onChange(value) {
      this.newItems = [];
      this.allEvents.forEach((eventElement) => {
        value.forEach((valueElement) => {
          if (eventElement.competenceName === valueElement.competenceName) {
            this.newItems.push(eventElement);
          }
        });
      });
      this.selectedEvents = this.sortArraysOnDate(this.newItems);
      this.selectedEvents = this.sortArraysOnDate(this.newItems);
      this.onDateChange();
    },
    onDateChange() {
      this.newItems = [];
      if (this.startDate === '') {
        this.startDate = null;
      }
      if (this.endDate === '') {
        this.endDate = null;
      }
      if (this.startDate !== null && this.endDate === null) {
        this.selectedEvents.forEach((element) => {
          if (element.date >= this.startDate) {
            this.newItems.push(element);
          }
        });
      } else if (this.startDate === null && this.endDate !== null) {
        this.selectedEvents.forEach((element) => {
          if (element.date <= this.endDate) {
            this.newItems.push(element);
          }
        });
      } else if (this.startDate !== null && this.endDate !== null) {
        this.selectedEvents.forEach((element) => {
          if (element.date >= this.startDate && element.date <= this.endDate) {
            this.newItems.push(element);
          }
        });
      } else {
        this.newItems = this.selectedEvents;
      }
      this.selectedEvents = this.sortArraysOnDate(this.newItems);
    },
  },
};
</script>
<style src="vue-multiselect/dist/vue-multiselect.min.css"></style>

<style scoped>
h3 {
  margin: 40px 0 0;
}

#calendar {
  max-width: 80%;
  width: 750px;
  margin: 40px auto;
}

li {
  list-style-type: none;
  position: relative;
  font-size: 1.5rem;
  padding: 15px;
  margin-bottom: 15px;
  background: lightgrey;
  color: #fff;
  margin: 0 30px 30px 30px;
  border-radius: 10px;
  color: black;
}

ul {
  position: relative;
  display: inline-block;
  width: 100%;
  margin-left: 29%;
}

#competenceName {
  margin: 0;
  margin-right: 125px;
}

#date {
  font-size: 0.5em;
  position: absolute;
  top: 10px;
  right: 10px;
}

#description {
  font-size: 0.7em;
}

#downloadCalendar {
  position: absolute;
  right: 10px;
  top: 50px;
  width: 66px;
}

#divDropdown {
  width: 20%;
  height: 80%;
  left: 20%;
  position: absolute;
  border-radius: 10px;
}

#dropdown {
  width: 100%;
  height: 100%;
}

label {
  display: block;
}

input {
  display: block;
  border-radius: 5px;
  border-width: 1px;
}
</style>
