from flask import Flask, request, jsonify
from snownlp import SnowNLP
from flask_cors import CORS
app = Flask(__name__)
app.config['JSON_AS_ASCII'] = False
CORS(app)
@app.route('/sentiment', methods=['POST'])
def sentiment_analysis():
    # 获取请求 JSON
    data = request.get_json()
    text = data.get('text', '').strip()  # 去掉前后空格

    if not text:
        return jsonify({'error': 'No text provided'}), 400

    # 情感分析
    s = SnowNLP(text)
    score = s.sentiments

    # 判断情绪类型并给出建议
    if score > 0.6:
        mood = "积极"
        advice = "保持良好的心态，继续加油哦～ 🌞"
    elif score < 0.4:
        mood = "消极"
        advice = "情绪低落时，可以试着和朋友聊聊或者听听喜欢的音乐。💬"
    else:
        mood = "中性"
        advice = "今天的你很平静，挺好的，保持放松吧～ 🌿"

    # 返回 JSON
    return jsonify({
        'text': text,
        'score': round(score, 3),
        'mood': mood,
        'advice': advice
    })

if __name__ == '__main__':
    # 指定 host 和 port，便于 Spring Boot 调用
    app.run(host='127.0.0.1', port=5000, debug=True)
