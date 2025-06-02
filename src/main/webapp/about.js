/**
 * 
 */

 // SVG icons as base64 encoded strings
const icons = {
    truck: `data:image/svg+xml;base64,${btoa(`
        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
            <path d="M10 17h4V5H2v12h3m15-5h-3V7h3m-3 5H9m11 0v5h-4"/>
            <circle cx="7" cy="17" r="2"/>
            <circle cx="17" cy="17" r="2"/>
        </svg>
    `)}`,
    clock: `data:image/svg+xml;base64,${btoa(`
        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
            <circle cx="12" cy="12" r="10"/>
            <polyline points="12 6 12 12 16 14"/>
        </svg>
    `)}`,
    award: `data:image/svg+xml;base64,${btoa(`
        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
            <circle cx="12" cy="8" r="6"/>
            <path d="M15.477 12.89 17 22l-5-3-5 3 1.523-9.11"/>
        </svg>
    `)}`
};

// Function to render team members
function renderTeamMembers() {
    const teamGrid = document.getElementById('teamGrid');
    teamGrid.innerHTML = teamMembers.map(member => `
        <div class="team-member">
            <img src="${member.image}" alt="${member.name}">
            <h3>${member.name}</h3>
            <p>${member.role}</p>
        </div>
    `).join('');
}

// Function to render statistics
function renderStatistics() {
    const statsGrid = document.getElementById('statsGrid');
    statsGrid.innerHTML = statistics.map(stat => `
        <div class="stat-item">
            <div class="number">${stat.number}</div>
            <div class="label">${stat.label}</div>
        </div>
    `).join('');
}

// Initialize icons
function initializeIcons() {
    document.querySelectorAll('.icon').forEach(icon => {
        const iconType = icon.classList[1].replace('-icon', '');
        icon.style.backgroundImage = `url('${icons[iconType]}')`;
        icon.style.filter = 'invert(37%) sepia(74%) saturate(1045%) hue-rotate(199deg) brightness(101%) contrast(107%)';
    });
}

// Initialize the page
document.addEventListener('DOMContentLoaded', () => {
    initializeIcons();
    renderTeamMembers();
    renderStatistics();
});