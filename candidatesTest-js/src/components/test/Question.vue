<template>
    <div class="content-question">
        <div class="title"><h6>{{question.text}}</h6></div>
         <div class="collection">
            <a v-for="ans in question.answers" :key="ans.id" class="collection-item"
                v-bind:class="{ 'selected': ans.selected }" 
                @click="selectAnswer(ans)">
                {{ ans.text }}
            </a>
        </div>
    </div>
</template>

<script>
export default {
    name: 'Question',
    props: {
        question: Object
    },

    methods: {
        selectAnswer: function(answer) {
            this.$set(answer, 'selected', true);
        }
    },
}
</script>

<style lang="scss">
.content-question {
    display: flex;
    flex-direction: column;
    align-items: center;

    .collection {
        width: 50%;

        a {
            cursor: pointer;

            &.selected {
                background: #eef5aa;
            }
        }
    }
}
</style>
