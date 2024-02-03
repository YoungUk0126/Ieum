import { ref } from 'vue';

export function useWebRTC(config) {
  const localPeerConnection = new RTCPeerConnection(config);
  const remotePeerConnection = new RTCPeerConnection(config);

  const localStream = ref(null);

  localPeerConnection.onicecandidate = (event) => {
    if (event.candidate) {
      remotePeerConnection.addIceCandidate(event.candidate);
    }
  };

  remotePeerConnection.onicecandidate = (event) => {
    if (event.candidate) {
      localPeerConnection.addIceCandidate(event.candidate);
    }
  };

  return { localPeerConnection, remotePeerConnection, localStream };
}