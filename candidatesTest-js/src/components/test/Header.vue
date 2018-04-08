<template>
    <nav class="white" role="navigation">
        <div class="nav-wrapper container">
            <a id="logo-container" href="#" class="brand-logo">Logo {{minutes}}</a>
        </div>
    </nav>
</template>

<script>
export default {
    name: 'Header',
    props: {
        minutes: Number
    },

    mounted: function() {
    }
}
</script>