import { ref } from 'vue';
import axios from 'axios';

export function useEmotion() {
    const text = ref('');
    const result = ref(null);
    const loading = ref(false);

    const analyzeEmotion = async () => {
        if (!text.value.trim()) {
            alert('请输入文字');
            return;
        }
        loading.value = true;
        try {
            const res = await axios.post('http://127.0.0.1:5000/sentiment', { text: text.value });
            result.value = res.data;
        } catch (error) {
            alert('请求失败，请检查后端是否启动');
            console.error(error);
        } finally {
            loading.value = false;
        }
    };

    const getProgressColor = (score, mood) => {
        if (mood === '积极') return '#4caf50';   // 绿色
        if (mood === '中性') return '#ffc107';   // 黄色
        if (mood === '消极') return '#f44336';   // 红色
        return '#2196f3';
    };

    return { text, result, loading, analyzeEmotion, getProgressColor };
}
