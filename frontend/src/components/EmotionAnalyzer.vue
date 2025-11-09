<template>
  <div class="container">
    <h1 class="title">心迹情感分析 💛</h1>
    <textarea v-model="text" placeholder="请输入你的心情..." rows="6"></textarea>
    <button @click="analyzeEmotion" :disabled="loading">
      {{ loading ? '分析中...' : '开始分析' }}
    </button>

    <div v-if="result" class="result">
      <p><strong>原文：</strong>{{ result.text }}</p>
      <p><strong>情绪：</strong>{{ result.mood }}</p>
      <p><strong>分数：</strong>{{ result.score }}</p>
      <div class="progress-bar-container">
        <div
            class="progress-bar"
            :style="{ width: (result.score * 100) + '%', backgroundColor: getProgressColor(result.score, result.mood) }"
        >
          {{ Math.round(result.score * 100) }}%
        </div>
      </div>
      <p><strong>建议：</strong>{{ result.advice }}</p>
    </div>
  </div>
</template>

<script setup>
import '../assets/style.css';
import { useEmotion } from '../composables/useEmotion.js';

const { text, result, loading, analyzeEmotion, getProgressColor } = useEmotion();
</script>
