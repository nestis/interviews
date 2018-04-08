<template>
    <div class="content-test">
        <Question :question="selectedQuestion "></Question>
        <div class="controls">
        </div>
    </div>
</template>

<script>
import Question from '@/components/test/Question'

export default {
    name: 'TestForm',
    components: { Question },
    data: function() {
        return {
            selectedQuestion: Object,
            answers: []
        }
    },
    props: {
        questions: Array
    },

    created: function() {
        this.selectedQuestion = this.questions[0];
    }
}
</script>

<style lang="scss">
.content-test {
    padding: 5%;
}
</style>
